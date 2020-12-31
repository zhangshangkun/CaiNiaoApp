package com.zsk.login.repo

import com.zsk.login.LoginService
import com.zsk.login.net.LoginReqBody

/**
 * @创建日期: 2020/12/31 10:59
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class LoginRepo (private val service:LoginService){
    suspend fun checkRegister(mobi:String){
       service.isRegister(mobi)
    }
    suspend  fun requestLogin(body:LoginReqBody){
        service.login(body)
    }
}