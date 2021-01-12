package com.zsk.cainiao5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zsk.cainiao5.databinding.ActivityMainBinding
import com.zsk.common.base.BaseActivity
import com.zsk.common.widget.BnvVp2Mediator
//import com.zsk.course.CourseFragment
//import com.zsk.home.HomeFragment
//import com.zsk.mine.MineFragment
//import com.zsk.study.StudyFragment

/**
* @创建日期: 2020/12/30 10:33
* @作者: zsk
* @描述: MainActivity
*/
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    private val fragments: Map<Int, Fragment> = mapOf<Int, Fragment>(
//        INDEX_HOME to HomeFragment(),
//        INDEX_COURSE to CourseFragment(),
//        INDEX_STUDY to StudyFragment(),
//        INDEX_MINE to MineFragment()
    )

    override fun initConfig() {
        super.initConfig()

    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vp2Main.adapter = MainViewPagerAdapter(this@MainActivity, fragments)
            BnvVp2Mediator(bnvMain, vp2Main) { bnv, vp2 ->
                vp2.isUserInputEnabled = false
            }.attach()
        }
    }


    companion object {
        const val INDEX_HOME = 0 //首页home对应的索引位置
        const val INDEX_COURSE = 1 //课程course对应的索引位置
        const val INDEX_STUDY = 2 //学习中心study对应的索引位置
        const val INDEX_MINE = 3 //我的mine对应的索引位置

    }
}

class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragments: Map<Int, Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragments[position] ?: error("请确保fragment数据源和viewPager2的index匹配设置")
    }
}