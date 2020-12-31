package com.zsk.login.repo

import com.zsk.login.net.LoginReqBody

/**
 * @创建日期: 2020/12/31 11:06
 * @作者: zsk
 * @描述: 登陆模块相关的抽象数据接口
 */
interface ILoginResource {
    /**
     * 检验手机号是否注册，合法
     */
    fun checkRegister(mobi: String)

    /**
     * 手机号合法的基础上，调用登录，获取登录结果token
     */
    fun requestLogin(body: LoginReqBody)
}