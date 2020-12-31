package com.zsk.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ToastUtils

/**
 * @创建日期: 2020/12/30 15:40
 * @作者: zsk
 * @描述: 登录界面逻辑的viewModel
 */
class LoginViewModel : ViewModel() {
    //账号，密码 Observable 对象
    val obMobile = ObservableField<String>()
    val obPassword = ObservableField<String>()

    /**
     * 调用登录，两步 1.判断手机号是否已经注册
     * 2.已经注册的，才去调用登录
     */
    fun goLogin() {

    }
    fun wechat(ctx:Context){
        ToastUtils.showShort("点击了微信登录")
    }
    fun qq(v:View){
        ToastUtils.showShort("点击qq登录")
    }
    fun weibo(){
        ToastUtils.showShort("点击微博登录")
    }
    fun AA(view: View){
         ToastUtils.showShort("静态点击方式")
    }
}