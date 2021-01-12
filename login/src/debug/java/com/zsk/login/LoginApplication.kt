package com.zsk.login

import com.zsk.common.BaseApplication
import com.zsk.service.moduleService
import org.koin.core.context.loadKoinModules

/**
 * @创建日期: 2021/1/4 15:28
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class LoginApplication: BaseApplication() {

    override fun initConfig() {
        super.initConfig()
        loadKoinModules(moduleService)
        loadKoinModules(moduleLogin)
    }
}