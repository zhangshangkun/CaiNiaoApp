package com.zsk.login

import com.zsk.common.network.KtRetrofit
import com.zsk.login.net.LoginService
import com.zsk.login.repo.ILoginResource
import com.zsk.login.repo.LoginRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @创建日期: 2020/12/25 11:04
 * @作者: zsk
 * @描述: 登录模块相关的koin的module配置
 */
val moduleLogin: Module = module {

    //service retrofit
    single {
        KtRetrofit.initConfig("http://course.api.cniao5.com/")
            .getService(LoginService::class.java)
    }

    //repo LoginResource
    single { LoginRepo(get()) } bind ILoginResource::class

    //viewModel
    viewModel{LoginViewModel(get())}
}