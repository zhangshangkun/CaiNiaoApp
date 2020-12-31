package com.zsk.common.widget

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @创建日期: 2020/12/29 17:11
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class BnvVp2Mediator(
    private val bnv: BottomNavigationView,
    private val vp2: ViewPager2,
    private val config:((BottomNavigationView,ViewPager2)->Unit)?
) {
    private val map: MutableMap<MenuItem, Int> = mutableMapOf<MenuItem, Int>()

    init {
        bnv.menu.forEachIndexed { index, item ->
            map[item] = index
        }
    }

    fun attach() {
        config?.invoke(bnv,vp2)
        vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bnv.selectedItemId = bnv.menu.getItem(position).itemId
            }
        })
        bnv.setOnNavigationItemSelectedListener { item ->
            vp2.currentItem = map[item] ?: error("Bnv的item的id${item.itemId}没有对应的viewPager2的元素")
            true
        }
    }
}