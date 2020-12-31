package com.zsk.common.network.config


import android.util.Log
import okhttp3.*
import java.net.URLDecoder
import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建日期: 2020/12/25 14:33
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class KtHttpLogInterceptor(block: (KtHttpLogInterceptor.() -> Unit)? = null) : Interceptor {

    private var logLevel: LogLevel = LogLevel.NONE //打印日期的标记
    private var colorLevel: ColorLevel = ColorLevel.DEBUG //默认是debug级别的logcat
    private var logTag: String = TAG //日志的logcat的Tag
    private var MILLIS_PATTERN:String = "yyyy-MM-dd HH:mm:ss"

    init {
        block?.invoke(this)
    }

    /**
     * 设置logLevel
     */
    fun logLevel(level: LogLevel): KtHttpLogInterceptor {
        logLevel = level
        return this
    }

    /**
     * 设置colorLevel
     */
    fun colorLevel(level: ColorLevel): KtHttpLogInterceptor {
        colorLevel = level
        return this
    }

    /**
     * 设置Log的Tag
     */
    fun logTag(tag: String): KtHttpLogInterceptor {
        logTag = tag
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        //请求
        val request: Request = chain.request()
        //响应
        return kotlin.runCatching { chain.proceed(request) }
            .onFailure {
                it.printStackTrace()
                logIt(
                    it.message.toString(),
                    ColorLevel.ERROR
                )
            }.onSuccess { response ->
                if (logLevel == LogLevel.NONE) {
                    return response
                }
                //记录请求日志
                logRequest(request, chain.connection())
                //记录响应日志
                logResponse(response)
            }.getOrThrow()
    }

    /**
     * 记录请求日志
     */
    private fun logRequest(request: Request, connection: Connection?) {
        val sb = StringBuilder()
        sb.appendln("\r\n")
        sb.appendln(
            "->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->"
        )
        when (logLevel) {
            LogLevel.NONE -> {

            }
            LogLevel.BASIC -> {
                logBasicReq(sb, request, connection)
            }
            LogLevel.HEADERS -> {
                logHeadersReq(sb, request, connection)
            }
            LogLevel.BODY -> {
                logBodyReq(sb, request, connection)
            }
        }
        sb.appendln(
            "->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->"
        )
        logIt(sb)
    }

    private fun logBodyReq(sb: StringBuilder, request: Request, connection: Connection?) {
        logHeadersReq(sb, request, connection)
        sb.appendln("RequestBody:${request.body.toString()}")
    }

    private fun logHeadersReq(sb: StringBuilder, request: Request, connection: Connection?) {
        logBasicReq(sb, request, connection)
        val headerStr: String = request.headers.joinToString { header ->
            "请求 Header:{${header.first}=${header.second}}\n"
        }
        sb.appendln(headerStr)
    }

    private fun logBasicReq(sb: StringBuilder, request: Request, connection: Connection?) {
        sb.appendln(
            "请求 method：${request.method} url:${decodeUrlStr(request.url.toString())} tag:" +
                    "${request.tag()} protocol:${connection?.protocol() ?: Protocol.HTTP_1_1}"
        )
    }

    /**
     * 记录响应日志
     */
    private fun logResponse(response: Response) {
        val sb = StringBuffer()
        sb.appendln("\r\n")
        sb.appendln("<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-")
        when (logLevel) {
            LogLevel.NONE -> {
                /*do nothing*/
            }
            LogLevel.BASIC -> {
                logBasicRep(sb,response)
            }
            LogLevel.HEADERS -> {
                logHeadersRep(response, sb)
            }
            LogLevel.BODY -> {
                logHeadersRep(response, sb)
                kotlin.runCatching {
                    //peek类似于clone数据流，监视，窥探，不能直接用原来的body的string流数据作为日志，会消费掉io，所有这里是peek，监测
                    val peekBody: ResponseBody = response.peekBody(1024 * 1024)
                    sb.appendln(peekBody.string())
                }.getOrNull()
            }
        }
        sb.appendln(
            "<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<" +
                    "-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-<<-"
        )
        logIt(sb, ColorLevel.INFO)
    }

    private fun logHeadersRep(response: Response, sb: StringBuffer) {
       logBasicRep(sb,response)
        val headerStr:String=response.headers.joinToString {
            header->"响应 Header:{${header.first}=${header.second}}\n"
        }
        sb.appendln(headerStr)
    }

    private fun logBasicRep(sb: StringBuffer, response: Response) {
         sb.appendln("响应 protocol:${response.protocol} code:${response.cacheControl} message:${response.message}")
             .appendln("响应 request Url: ${decodeUrlStr(response.request.url.toString())}")
             .appendln("响应 sentRequestTime:${toDateTimeStr(
                 response.sentRequestAtMillis,
                 MILLIS_PATTERN
             )} receivedResponseTime:${
                    toDateTimeStr(
                        response.receivedResponseAtMillis,
                        MILLIS_PATTERN
                    )
             }")
    }


    /**
     * 对于url编码的string解码
     */
    private fun decodeUrlStr(url: String): String? {
        return kotlin.runCatching {
            URLDecoder.decode(url, "utf-8")
        }.onFailure { it.printStackTrace() }.getOrNull()
    }

    /**
     * 打印日志
      */
    private fun logIt(any: Any, tempLevel: ColorLevel? = null) {
        when (tempLevel ?: colorLevel) {
            ColorLevel.VERBOSE -> Log.v(logTag, any.toString())
            ColorLevel.DEBUG -> Log.d(logTag, any.toString())
            ColorLevel.INFO -> Log.i(logTag, any.toString())
            ColorLevel.WARN -> Log.w(logTag, any.toString())
            ColorLevel.ERROR -> Log.e(logTag, any.toString())

        }
    }

    companion object {
        private const val TAG = "<KtHttp>" //默认的TAG

        //时间格式化
        fun toDateTimeStr(millis: Long, pattern: String): String {
            return SimpleDateFormat(pattern, Locale.getDefault()).format(millis)
        }
    }

    /**
     * 打印日志范围
     */
    enum class LogLevel {
        NONE,//不打印
        BASIC,//只打印行首，请求/响应
        HEADERS, //打印请求和响应的所有header
        BODY //打印所有
    }

    /**
     * Log颜色等级，应用于android Logcat分为 v、d、i、w、e
     */
    enum class ColorLevel {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}