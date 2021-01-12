package com.zsk.login.repo

import androidx.lifecycle.LiveData
import com.zsk.login.net.LoginReqBody
import com.zsk.login.net.LoginRsp
import com.zsk.login.net.RegisterRsp

/**
 * @创建日期: 2020/12/31 11:06
 * @作者: zsk
 * @描述: 登陆模块相关的抽象数据接口
 */
interface ILoginResource {
    //注册与否的检查结果
    val registerRsp:LiveData<RegisterRsp>

    //登录成功后的结果
    val loginRsp:LiveData<LoginRsp>
    /**
     * 检验手机号是否注册，合法
     */
    suspend fun checkRegister(mobi: String)

    /**
     * 手机号合法的基础上，调用登录，获取登录结果token
     */
    suspend fun requestLogin(body: LoginReqBody)
}