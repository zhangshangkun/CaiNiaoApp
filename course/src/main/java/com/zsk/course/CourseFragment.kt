package com.zsk.course

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.zsk.common.base.BaseFragment
import com.zsk.course.databinding.FragmentCourseBinding

/**
 * @创建日期: 2020/12/24 15:58
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class CourseFragment :BaseFragment(){

    override fun getLayoutRes(): Int {
        return R.layout.fragment_course
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view)
    }
}