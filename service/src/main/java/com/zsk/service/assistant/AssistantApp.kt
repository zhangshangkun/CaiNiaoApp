package com.zsk.service.assistant

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit

/**
 * @创建日期: 2020/12/25 11:21
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
object AssistantApp {
    fun initConfig(application: Application) {
//        DoraemonKit.install(application, mutableListOf(ServerHostKit()))
        DoraemonKit.install(application)
    }
}

