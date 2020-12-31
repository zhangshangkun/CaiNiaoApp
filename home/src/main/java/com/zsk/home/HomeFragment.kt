package com.zsk.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.zsk.common.base.BaseFragment
import com.zsk.home.databinding.FragmentHomeBinding

/**
 * @创建日期: 2020/12/24 15:56
 * @作者: zsk
 * @描述: 首页
 */
class HomeFragment:BaseFragment() {
    override fun getLayoutRes(): Int {
       return R.layout.fragment_home
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view)
    }
}