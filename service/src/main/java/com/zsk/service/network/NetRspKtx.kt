package com.zsk.service.network

import androidx.annotation.Keep
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.zsk.common.model.DataResult
import com.zsk.common.model.succeeded
import com.zsk.common.network.support.CnUtils
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @创建日期: 2020/12/30 17:56
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
@Keep
data class BaseCniaoRsp(
    val code: Int, //响应码
    val data: String?, //响应数据内容
    val message: String? //响应数据结果描述
) {
    companion object {
        const val SERVER_CODE_FAILED = 0  //接口请求响应业务处理 失败
        const val SERVER_CODE_SUCCESS = 1 //接口请求响应业务处理 成功
    }
}

/**
 * 这里表示网络请求成功，并得到业务服务器的响应，至于业务成功失败，另一说
 * 将BaseCniaoRsp的对象，转化为需要的对象类型，也就是将body.string转为entity
 * @return 返回需要类型对象，可能为null，如果json解析失败的话
 */
inline fun <reified T> BaseCniaoRsp.toEntity(): T? {
    if (data == null) {
        LogUtils.e("server Response Json Ok,But data==null,$code,$message")
        return null
    }
    //gson不允许我们将json对象采用String，所有单独处理
    if (T::class.java.isAssignableFrom(String::class.java)) {
        return CnUtils.decodeData(this.data) as T
    }
    return kotlin.runCatching {
        GsonUtils.fromJson(CnUtils.decodeData(this.data), T::class.java)
    }.onFailure { e ->
        e.printStackTrace()
    }.getOrNull()
}

/**
 * 接口成功，但是业务返回code不是1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseCniaoRsp.onBizError(crossinline block: (code: Int, message: String) -> Unit): BaseCniaoRsp {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    if (code != BaseCniaoRsp.SERVER_CODE_SUCCESS) {
        block.invoke(code, message ?: "Error Message Null")
    }
    return this
}

/**
 * 接口成功且业务成功code==1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseCniaoRsp.onBizOK(crossinline action: (code: Int, data: T?, message: String?) -> Unit): BaseCniaoRsp {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (code == BaseCniaoRsp.SERVER_CODE_SUCCESS) {
        action.invoke(code, this.toEntity<T>(), message)
    }
    return this
}

/**
 * 扩展用于处理网络返回数据结果，网络接口请求成功过，但是业务成功与否，另一说
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit): DataResult<R> {

    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (succeeded) action.invoke((this as DataResult.Success).data)
    return this
}

/**
 * 这是网络请求出现错误的时候的回调
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(action: (exception: Throwable) -> Unit): DataResult<R> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(exception)
    return this
}
