package com.teambeme.beme.data.remote.api

import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.RequestSignUp
import com.teambeme.beme.signup.model.ResponseSignUp
import retrofit2.http.*

interface SignUpService {
    @Headers("Content-type: application/json")
    @POST("users/signup")
    suspend fun signUp(
        @Body body: RequestSignUp
    ): ResponseSignUp

    @Headers("Content-type: application/json")
    @GET("users")
    suspend fun nickDoubleCheck(
        @Query("nickname") nickName: String
    ): ResponseNickDoubleCheck
}