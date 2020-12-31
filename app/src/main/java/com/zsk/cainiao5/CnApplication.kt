package com.zsk.cainiao5

import com.zsk.common.BaseApplication
import com.zsk.common.ktx.application
import com.zsk.service.assistant.AssistantApp

/**
 * @创建日期: 2020/12/25 12:22
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class CnApplication:BaseApplication() {

    override fun initConfig() {
        super.initConfig()
        AssistantApp.initConfig(application)
    }
}