package com.teambeme.beme.data.remote.singleton

import com.teambeme.beme.data.remote.api.LoginService
import com.teambeme.beme.data.remote.api.OtherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjects {
    private const val BASE_URL = "http://15.164.67.58:3000/"
    val loggingInterceptor = HttpLoggingInterceptor()

    fun addLoggingInterceptor(): HttpLoggingInterceptor {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val baseRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(addLoggingInterceptor())
                .build()
        )
        .build()

    private var loginInstance: LoginService? = null
    fun getLoginService(): LoginService = loginInstance ?: synchronized(this) {
        loginInstance ?: baseRetrofit.create(LoginService::class.java).apply {
            loginInstance = this
        }
    }

    private var otherPageInstance: OtherService? = null
    fun getOtherPageService(): OtherService = otherPageInstance ?: synchronized(this) {
        otherPageInstance ?: baseRetrofit.create(OtherService::class.java).apply {
            otherPageInstance = this
        }
    }
}