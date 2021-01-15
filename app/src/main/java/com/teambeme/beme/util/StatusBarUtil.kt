package com.teambeme.beme.util

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View

object StatusBarUtil {
    @SuppressLint("InlinedApi")
    @Suppress("DEPRECATION")
    fun setStatusBar(activity: Activity, color: Int) {
        activity.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window?.statusBarColor = color
    }
}