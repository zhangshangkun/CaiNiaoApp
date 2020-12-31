package com.zsk.service.network

import androidx.annotation.Keep

/**
 * @创建日期: 2020/12/30 17:56
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
@Keep
data class BaseCniaoRsp (
    val code:Int, //响应码
    val data:String?, //响应数据内容
    val message:String? //响应数据结果描述
){
    companion object{
        const val SERVER_CODE_FAILED=0  //接口请求响应业务处理 失败
        const val SERVER_CODE_SUCCESS=1 //接口请求响应业务处理 成功
    }
}
