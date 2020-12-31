package com.zsk.common.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.zsk.common.ktx.bindView
import com.zsk.common.ktx.viewLifeCycleOwner

/**
 * @创建日期: 2020/12/23 17:31
 * @作者: zsk
 * @描述: 简单封装基类Activity
 */
abstract class BaseActivity<ActBinding : ViewDataBinding> : AppCompatActivity {

    constructor() : super()

    constructor(@LayoutRes layout: Int) : super(layout)

    protected lateinit var mBinding: ActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = bindView<ActBinding>(getLayoutRes())
        initConfig()
        initView()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun initConfig() {
    }

    open fun initView() {

    }

    open fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }

    protected fun <T : Any> LiveData<T>.observerKt(block: (T?) -> Unit) {
        this.observe(viewLifeCycleOwner, Observer { data ->
//          block.invoke(it) //一样方式
            block(data)
        })
    }
}