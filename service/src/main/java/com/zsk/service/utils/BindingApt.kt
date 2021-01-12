package com.zsk.service.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @创建日期: 2021/1/7 17:57
 * @作者: zsk
 * @描述: 项目适配用的BindAdapter
 */

/**
 * imageView支持图片加载 绑定
 */
@BindingAdapter("app:srcCompat")
fun imgSrc(iv: ImageView, src: Any?) {
    Glide.with(iv)
        .load(src)
        .into(iv)
}

@BindingAdapter("app:textColor")
fun imgColor(iv: ImageView,@ColorRes color: Int) {
    if (color != 0) iv.setColorFilter(iv.resources.getColor(color))
}

@BindingAdapter("app:tint")
fun imgColor2(iv: ImageView,color: Int) {
    if (color==0) return
    runCatching {
        iv.setColorFilter(iv.resources.getColor(color))
    }.onFailure {
        iv.setColorFilter(color)
    }
}

@BindingAdapter("android:textColor")
fun tvColor(tv: TextView,@ColorRes color: Int) {
    if (color != 0) tv.setTextColor(tv.resources.getColor(color))
}

@BindingAdapter("android:textColor")
fun tvColor2(tv: TextView, color: Int) {
    if (color==0) return
    runCatching {
        tv.setTextColor(tv.resources.getColor(color))
    }.onFailure {
        tv.setTextColor(color)
    }
}