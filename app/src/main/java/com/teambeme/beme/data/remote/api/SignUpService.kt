package com.teambeme.beme.data.remote.api

import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface SignUpService {
    @Multipart
    @POST("users/signup")
    suspend fun signUp(
        @PartMap body: HashMap<String, RequestBody>,
        @Part file: MultipartBody.Part?
    ): ResponseSignUp

    @Headers("Content-type: application/json")
    @GET("users")
    suspend fun nickDoubleCheck(
        @Query("nickname") nickName: String
    ): ResponseNickDoubleCheck
}