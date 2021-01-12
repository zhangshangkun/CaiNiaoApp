package com.zsk.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.zsk.mine.R
import com.zsk.mine.databinding.VItemSettingsBinding
import kotlinx.android.synthetic.main.v_item_settings.view.*

/**
 * @创建日期: 2021/1/7 17:37
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class ItemSettingsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var itemBean = ItemSettingsBean()
    private var obItemInfo = ObservableField<ItemSettingsBean>(itemBean)

    init {
        //1.管理布局layout
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            info = obItemInfo
        }
        setBackgroundColor(Color.WHITE)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView)
            .apply {
                //Icon设置
                itemBean.iconRes =
                    getResourceId(R.styleable.ItemSettingsView_icon, R.drawable.ic_gift_card)
                val iconRGB: Int = getColor(R.styleable.ItemSettingsView_iconColor, 0)
                itemBean.iconColor = iconRGB
                //title设置
                itemBean.title=getString(R.styleable.ItemSettingsView_title)?:"Title标题"
                val titleRGB:Int=getColor(R.styleable.ItemSettingsView_titleColor,resources.getColor(R.color.colorPrimaryText))
                itemBean.titleColor=titleRGB
                //desc设置
                itemBean.desc=getString(R.styleable.ItemSettingsView_desc)?:"标题内容描述"
                val descRGB:Int=getColor(R.styleable.ItemSettingsView_descColor,0)
                itemBean.descColor=descRGB
                //arrow设置
                itemBean.arrowRes=getResourceId(R.styleable.ItemSettingsView_arrow,R.drawable.ic_right)
                val arrowRGB=getColor(R.styleable.ItemSettingsView_arrowColor,resources.getColor(R.color.colorSecondaryText))
                itemBean.arrowColor=arrowRGB

            }
        //回收
        attributes.recycle()
    }
    //region 设置资源
    /**
     * 设置整个item的对象info
     */
    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInfo.set(info)
    }

    /**
     * 设置title
     */
    fun setTitle(title: String) {
        itemBean.title = title
    }

    /**
     * 设置内容描述
     */
    fun setDesc(desc: String) {
        itemBean.desc = desc
    }

    /**
     * 设置icon图标
     */
    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
    }

    /**
     * 设置icon图标
     */
    fun setArrow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
    }
    //endregion

    //region 点击事件
    /**
     * 单独点击icon
     */
    fun onClickIcon(listener: OnClickListener) {
        itemBean.iconListener = listener
    }

    /**
     * 单独点击title
     */
    fun onClickTitle(listener: OnClickListener) {
        itemBean.titleListener = listener
    }

    /**
     * 单独点击desc
     */
    fun onClickDesc(listener: OnClickListener) {
        itemBean.descListener = listener
    }

    /**
     * 单独点击arrow
     */
    fun onClickArrow(listener: OnClickListener) {
        itemBean.arrowListener = listener
    }
    //endregion

    //region 设置颜色
    /**
     * 设置icon颜色
     */
    fun onIconColor(colorRes: Int) {
        itemBean.iconColor = colorRes
    }

    /**
     * 设置title颜色
     */
    fun onTitleColor(colorRes: Int) {
        itemBean.titleColor = colorRes
    }

    /**
     * 设置desc颜色
     */
    fun onDescColor(colorRes: Int) {
        itemBean.descColor = colorRes
    }

    /**
     * 设置箭头颜色
     */
    fun onArrowColor(colorRes: Int) {
        itemBean.arrowColor = colorRes
    }
    //endregion
    /**
     * 重写拦截事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasOnClickListeners()
    }
}

@Keep
data class ItemSettingsBean(
    var iconRes: Any = R.drawable.ic_gift_card,
    var title: String = "Title标题",
    var desc: String = "标题内容描述",
    var titleColor: Int = R.color.colorPrimaryText,
    var descColor: Int = R.color.colorPrimaryText,
    var iconColor: Int = 0,
    var arrowColor: Int = 0,
    var arrowRes: Any = R.drawable.ic_right
) {
    //item的子View的点击listener
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null
}