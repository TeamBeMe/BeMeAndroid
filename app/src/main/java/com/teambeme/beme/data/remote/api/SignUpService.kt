package com.teambeme.beme.data.remote.api

import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
    @Headers("Content-type: application/json")
    @POST("users/signup")
    fun signUp()
}