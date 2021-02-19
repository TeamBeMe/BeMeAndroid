package com.teambeme.beme.util

import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        BeMeAuthPreference.userToken.let {
            requestBuilder.addHeader("token", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}