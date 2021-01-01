package com.teambeme.beme.util

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.View

object TransparentStatusBarObject {
    @SuppressLint("InlinedApi")
    @Suppress("DEPRECATION")
    fun setStatusBar(activity: Activity) {
        activity.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window?.statusBarColor = Color.TRANSPARENT
    }
}