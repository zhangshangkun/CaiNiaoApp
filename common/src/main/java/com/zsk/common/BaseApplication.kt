package com.zsk.common

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @创建日期: 2020/12/23 11:38
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
abstract class BaseApplication:Application() {
    
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR) //log level Error方能保证这句话不会报错，要么就不写这个
            //context
            androidContext(this@BaseApplication)
        }
        initConfig()
        initData()
        LogUtils.d("BaseApplication onCreate")
    }

    /**
     * 可用于必要的配置初始化
     */
    protected open fun initConfig() {

    }
    /**
     * 可用于子类实现必要的数据初始化
     */
    protected  open fun initData() {
    }
}