package com.zsk.common.network

import com.zsk.common.network.support.IHttpCallBack

/**
 * @创建日期: 2020/12/25 15:42
 * @作者: zsk
 * @描述: 网络请求的统一接口类
 */
interface HttpApi {

    /**
     * 抽象的http的get请求封装 异步
     */
    fun get(params: Map<String, Any>, path: String, callBack: IHttpCallBack)

    /**
     * 抽象的http的get请求封装 同步
     */
    fun getSync(params: Map<String, Any>, path: String): Any? {
        return Any()
    }

    /**
     * 抽象的http的post请求封装 异步
     */
    fun post(body: Any, path: String, callBack: IHttpCallBack)

    /**
     * 抽象的http的post请求封装 同步
     */
    fun postSync(body: Any, path: String): Any? = Any()

    fun cancelRequest(tag:Any)

    fun cancelAllRequest()
}