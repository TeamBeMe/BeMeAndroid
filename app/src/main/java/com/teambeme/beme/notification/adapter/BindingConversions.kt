package com.teambeme.beme.notification.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.teambeme.beme.R

object BindingConversions {

    @BindingAdapter("userPicUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url : String){
        Glide.with(imageView.context).load(url).error(R.drawable.ic_default_profile_pic).into(imageView)
    }
}