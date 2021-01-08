package com.teambeme.beme

import android.app.Application
import com.teambeme.beme.util.PixelRatio

class BeMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPixelUtil()
    }

    private fun initPixelUtil() {
        pixelRatio = PixelRatio(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}