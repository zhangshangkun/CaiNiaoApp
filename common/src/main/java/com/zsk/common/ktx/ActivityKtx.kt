package com.zsk.common.ktx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

/**
 * @创建日期: 2020/12/23 16:53
 * @作者: zsk
 * @描述: Activity相关的ktx,扩展函数或者扩展属性
 */

//region 扩展函数
/**
 * Activity中使用DataBinding时setContentView的简化
 * [layout] 布局文件
 * @return 返回一个Binding的对象实例
 */
fun <T:ViewDataBinding> Activity.bindView(@LayoutRes layout:Int):T{
    return DataBindingUtil.setContentView(this,layout)
}

/**
 * Activity中使用DataBinding时setContentView的简化
 * [layout] 布局文件
 * @return 返回一个Binding的对象实例 T类型可null的
 */
fun <T:ViewDataBinding> Activity.bindView(view: View):T?{
    return DataBindingUtil.bind<T>(view)
}

fun Activity.immediateStatusBar(){
    window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}

/**
 * 软键盘的隐藏
 * [view] 时间控制View
 */
fun Activity.dismissKeyBoard(view:View){
    val imm:InputMethodManager?=getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm ?.hideSoftInputFromWindow(view.windowToken,0)
}
//endregion

//region 扩展属性
val ComponentActivity.viewLifeCycleOwner:LifecycleOwner
     get() = this
val Activity.context:Context
     get() = this
//endregion