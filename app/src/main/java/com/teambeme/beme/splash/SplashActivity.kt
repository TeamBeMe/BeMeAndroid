package com.teambeme.beme.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowInsets.*
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teambeme.beme.R
import com.teambeme.beme.data.remote.datasource.LoginDataSourceImpl
import com.teambeme.beme.data.remote.singleton.BeMeAuthPreference
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.login.repository.LoginRepositoryImpl
import com.teambeme.beme.login.view.LoginActivity
import com.teambeme.beme.main.view.MainActivity
import com.teambeme.beme.util.ErrorBody
import com.teambeme.beme.util.StatusBarUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    private val loginRepository =
        LoginRepositoryImpl(LoginDataSourceImpl(RetrofitObjects.getLoginService()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setStatusBar(this, R.color.black)
        val handler = Handler(Looper.getMainLooper())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(Type.statusBars() or Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
        val thread = object : Thread() {
            override fun run() {
                runOnUiThread {
                    handler.postDelayed({
                        if (BeMeAuthPreference.userId.isEmpty()) {
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            loginRepository.login(
                                BeMeAuthPreference.userId,
                                BeMeAuthPreference.userPassword
                            ).enqueue(object :
                                Callback<ResponseLogin> {
                                override fun onResponse(
                                    call: Call<ResponseLogin>,
                                    response: Response<ResponseLogin>
                                ) {
                                    if (response.isSuccessful) {
                                        BeMeAuthPreference.userToken =
                                            response.body()!!.data!!.token
                                        val intent =
                                            Intent(this@SplashActivity, MainActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        startActivity(intent)
                                    } else {
                                        val gson = Gson()
                                        val type = object : TypeToken<ErrorBody>() {}.type
                                        val errorResponse: ErrorBody? =
                                            gson.fromJson(response.errorBody()!!.charStream(), type)
                                    }
                                }

                                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                                    Log.d("Network Fail", t.message.toString())
                                    for (element in t.stackTrace) {
                                        Log.d("Network element", element.toString())
                                        Log.d("Network className", element.className)
                                        Log.d("Network methodName", element.methodName)
                                        Log.d("Network fileName", element.fileName)
                                        Log.d("Network lineNumber", element.lineNumber.toString())
                                    }
                                }
                            })
                        }
                    }, 1000)
                }
            }
        }

        thread.start()
    }
}
