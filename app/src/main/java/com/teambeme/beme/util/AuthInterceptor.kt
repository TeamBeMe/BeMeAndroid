package com.teambeme.beme.util

import com.teambeme.beme.data.local.singleton.BeMeRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        BeMeRepository.userToken.let {
            requestBuilder.addHeader("token", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}
