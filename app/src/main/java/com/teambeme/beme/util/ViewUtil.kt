package com.teambeme.beme.util

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes

fun View.color(@ColorRes color: Int): Int = resources.getColor(color, null)
var ImageView.imageSrc: Int
    get() = this.tag as Int
    set(value) = this.setImageResource(value)