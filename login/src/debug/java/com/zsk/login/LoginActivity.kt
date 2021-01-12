package com.zsk.login

import com.blankj.utilcode.util.ToastUtils
import com.zsk.common.base.BaseActivity
import com.zsk.login.databinding.ActivityLoginBinding
import com.zsk.login.net.RegisterRsp
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @创建日期: 2020/12/30 14:33
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModel()

    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }


    override fun initView() {
        super.initView()
        mBinding.apply {
            vm = viewModel
            mtoolbarLogin.setNavigationOnClickListener { finish() }
            tvRegisterLogin.setOnClickListener {
                ToastUtils.showShort("当前课程项目未实现注册账号功能!")
            }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {
            liveRegisterRsp.observerKt {
                if (it?.is_register == RegisterRsp.FLAG_IS_REGISTERED) {
                    repoLogin()
                }
            }
            liveLoginRsp.observerKt {
                ToastUtils.showShort("登录结果" + it.toString())
            }
        }

    }

    override fun initData() {
        super.initData()
    }
}