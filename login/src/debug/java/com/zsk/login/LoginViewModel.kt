package com.zsk.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.ToastUtils
import com.zsk.common.base.BaseViewModel
import com.zsk.login.net.LoginReqBody
import com.zsk.login.net.LoginRsp
import com.zsk.login.net.RegisterRsp
import com.zsk.login.repo.ILoginResource

/**
 * @创建日期: 2020/12/30 15:40
 * @作者: zsk
 * @描述: 登录界面逻辑的viewModel
 */
class LoginViewModel(private val resource: ILoginResource) : BaseViewModel() {
    //账号，密码 Observable 对象
    val obAccount = ObservableField<String>()
    val obPassword = ObservableField<String>()

    val liveRegisterRsp: LiveData<RegisterRsp> = resource.registerRsp
    val liveLoginRsp: LiveData<LoginRsp> = resource.loginRsp

    /**
     * 检查是否注册的账号
     */
    private fun checkRegister(mobi: String) = serverAwait {
        resource.checkRegister(mobi)
    }

    /**
     * 调用登录
     * val mobi:String="18648957777"
     * val password:String="cn5123456"
     */
    internal fun repoLogin() {
        val account: String = obAccount.get() ?: return
        val password: String = obPassword.get() ?: return
        serverAwait {
            resource.requestLogin(LoginReqBody(account, password))
        }
    }

    /**
     * 调用登录，两步 1.判断手机号是否已经注册
     * 2.已经注册的，才去调用登录
     */
    fun goLogin() {
        val account: String = obAccount.get() ?: return
        checkRegister(account)
    }

    fun wechat(ctx: Context) {
        ToastUtils.showShort("点击了微信登录")
    }

    fun qq(v: View) {
        ToastUtils.showShort("点击qq登录")
    }

    fun weibo() {
        ToastUtils.showShort("点击微博登录")
    }

    fun AA(view: View) {
        ToastUtils.showShort("静态点击方式")
    }
}