package com.teambeme.beme

import android.app.Application
import com.teambeme.beme.data.local.singleton.BeMeRepository
import com.teambeme.beme.util.PixelRatio
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPixelUtil()
        BeMeRepository.init(this)
    }

    private fun initPixelUtil() {
        pixelRatio = PixelRatio(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}
