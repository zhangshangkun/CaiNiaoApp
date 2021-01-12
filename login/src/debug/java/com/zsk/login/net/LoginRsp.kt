package com.zsk.login.net

import androidx.annotation.Keep

/**
 * @创建日期: 2020/12/31 10:24
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
@Keep
data class RegisterRsp(
    val is_register: Int = FLAG_UN_REGISTERED //1表示注册 0表示未注册
) {
    companion object {
        const val FLAG_IS_REGISTERED = 1//已经注册
        const val FLAG_UN_REGISTERED = 0 //未注册
    }

}

@Keep
data class LoginRsp(
    val course_permission: Boolean,
    val token: String?,
    val user: User?
) {
    @Keep
    data class User(
        val id: Int,//用户id
        val logo_url: String?,//用户头像
        val reg_time: String?,//用户注册时间
        val username: String? //用户名
    )
}