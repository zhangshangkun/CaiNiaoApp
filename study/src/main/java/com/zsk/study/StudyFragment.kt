package com.zsk.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.zsk.common.base.BaseFragment
import com.zsk.study.databinding.FragmentStudyBinding

/**
 * @创建日期: 2020/12/24 16:03
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class StudyFragment : BaseFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_study
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view)
    }
}