package com.teambeme.beme.data.remote.api

import com.teambeme.beme.mypage.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface MyPageService {
    @Multipart
    @PUT("/profiles")
    fun putProfile(
        @Part file: MultipartBody.Part?
    ): Call<ResponseProfile>

    @Headers("Content-type: application/json")
    @GET("profiles/answers")
    fun getMyAnswer(
        @Query("public") public: String?,
        @Query("category") category: Int?,
        @Query("query") query: String?,
        @Query("page") page: Int
    ): Call<ResponseMyAnswer>

    @Headers("Content-type: application/json")
    @GET("profiles")
    fun getMyProfile(): Call<ResponseMyProfile>

    @Headers("Content-type: application/json")
    @GET("profiles/scraps")
    fun getMyScrap(
        @Query("public") public: String?,
        @Query("category") category: Int?,
        @Query("query") query: String?,
        @Query("page") page: Int
    ): Call<ResponseMyScrap>

    @Headers("Content-type: application/json")
    @PUT("home/public")
    fun putPublic(
        @Body body: RequestPublic
    ): Call<ResponsePublic>
}