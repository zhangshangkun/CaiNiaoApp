package com.zsk.login.net

import androidx.annotation.Keep

/**
 * @创建日期: 2020/12/31 10:23
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
@Keep
data class LoginReqBody(
    val mobile: String,
    val password: String
)