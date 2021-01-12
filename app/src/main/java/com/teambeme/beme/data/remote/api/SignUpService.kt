package com.teambeme.beme.data.remote.api

import com.teambeme.beme.signup.model.RequestSignUp
import com.teambeme.beme.signup.model.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
    @Headers("Content-type: application/json")
    @POST("users/signup")
    suspend fun signUp(
        @Body body: RequestSignUp
    ): ResponseSignUp
}