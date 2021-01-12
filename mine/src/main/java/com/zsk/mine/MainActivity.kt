package com.zsk.mine

import android.graphics.Color
import androidx.databinding.ObservableField
import com.zsk.common.base.BaseActivity
import com.zsk.mine.databinding.ActivityMainBinding
import com.zsk.mine.widget.ItemSettingsBean


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            var ib = ItemSettingsBean(title = "学习卡")

            val obBean: ObservableField<ItemSettingsBean> = ObservableField(ib)
            bean = obBean

            ib.title = "你好，同学啊"
//            ib.titleColor = R.color.colorAccent
            ib.arrowColor=R.color.colorAccent

        }
    }
}