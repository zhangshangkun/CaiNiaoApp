package com.zsk.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.zsk.common.base.BaseFragment
import com.zsk.mine.databinding.FragmentMineBinding

/**
 * @创建日期: 2020/12/24 16:02
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class MineFragment : BaseFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_mine
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view)
    }
}