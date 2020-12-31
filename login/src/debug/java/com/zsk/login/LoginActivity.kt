package com.zsk.login

import androidx.activity.viewModels
import com.blankj.utilcode.util.ToastUtils
import com.zsk.common.base.BaseActivity
import com.zsk.login.databinding.ActivityLoginBinding

/**
 * @创建日期: 2020/12/30 14:33
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }

    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun initConfig() {
        super.initConfig()
        mBinding.apply {
            vm = viewModel
            mtoolbarLogin.setNavigationOnClickListener { finish() }
            tvRegisterLogin.setOnClickListener {
                ToastUtils.showShort("当前课程项目未实现注册账号功能!")
            }
        }
    }

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
    }
}