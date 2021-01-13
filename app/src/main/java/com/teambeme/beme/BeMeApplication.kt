package com.teambeme.beme

import android.app.Application
import com.teambeme.beme.data.remote.singleton.BeMeAuthPreference
import com.teambeme.beme.util.PixelRatio

class BeMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPixelUtil()
        BeMeAuthPreference.init(this)
    }

    private fun initPixelUtil() {
        pixelRatio = PixelRatio(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}