package com.teambeme.beme.data.remote.api

import com.teambeme.beme.otherpage.model.*
import retrofit2.Call
import retrofit2.http.*

interface OtherService {
    @Headers(
        "Accept: application/json",
        "Content-type: application/json"
    )
    @GET("profiles/answers/{user_id}")
    fun getProfileAnswer(
        @Header("token") token: String,
        @Path("user_id") userId: Int,
        @Query("page") page: Int
    ): Call<ResponseOtherData>

    @Headers("Content-type: application/json")
    @GET("profiles/{user_id}")
    fun getOtherInfo(
        @Header("token") token: String,
        @Path("user_id") userId: Int
    ): Call<ResponseOtherInfo>

    @Headers("Content-type: application/json")
    @PUT("exploration/{answerId}")
    fun putScrap(
        @Header("token") token: String,
        @Path("answerId") answerId: Int
    ): Call<ResponseScrap>

    @Headers("Content-type: application/json")
    @PUT("follow")
    fun putFollow(
        @Header("token") token: String,
        @Body user_id: RequestFollow
    ): Call<ResponseFollow>
}