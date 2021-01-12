package com.teambeme.beme

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.teambeme.beme.util.PixelRatio

class BeMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initPixelUtil()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("BeMeApplication.TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("BeMeApplication.TAG", msg)
        })
    }

    private fun initPixelUtil() {
        pixelRatio = PixelRatio(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
    }
}