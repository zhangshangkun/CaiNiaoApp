package com.zsk.common.model

import androidx.lifecycle.LiveData

/**
 * @创建日期: 2020/12/23 18:06
 * @作者: zsk
 * @描述: 创建一个空的LiveData的对象类
 */
class AbsentLifeData<T : Any?> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T : Any?> create(): LiveData<T> {
            return AbsentLifeData()
        }
    }
}