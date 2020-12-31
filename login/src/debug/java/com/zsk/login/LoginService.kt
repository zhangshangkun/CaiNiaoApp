package com.zsk.login

import com.zsk.login.net.LoginReqBody
import com.zsk.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @创建日期: 2020/12/30 17:46
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
interface LoginService {

    @GET("accounts/phone/is/register")
    suspend fun isRegister(@Query("mobi") mobi:String): Call<BaseCniaoRsp>

    @POST("accounts/course/10301/login")
    suspend fun login(@Body reqBody: LoginReqBody):Call<BaseCniaoRsp>
}