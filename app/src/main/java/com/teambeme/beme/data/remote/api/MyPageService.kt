package com.teambeme.beme.data.remote.api

import com.teambeme.beme.mypage.model.ResponseProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyPageService {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @Multipart
    @POST("profiles")
    fun putProfile(
        @Part("profile_img") profileImg: RequestBody,
        @Part url: MultipartBody.Part,
        @Header("token") token: String
    ): Call<ResponseProfile>
}