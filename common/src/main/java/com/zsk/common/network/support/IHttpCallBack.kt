package com.zsk.common.network.support

/**
 * @创建日期: 2020/12/25 16:08
 * @作者: zsk
 * @描述: 网络请求接口回调
 */
interface IHttpCallBack {
    /**
     * 网络请求成功回调
     * [data] 返回回调的数据结果
     */
    fun onSuccess(data:Any?)
    /**
     * 网络请求失败回调
     */
    fun onFailed(error: Any?)
}