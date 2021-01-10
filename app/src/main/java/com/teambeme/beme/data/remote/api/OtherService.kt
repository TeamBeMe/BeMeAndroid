package com.teambeme.beme.data.remote.api

import com.teambeme.beme.otherpage.model.ResponseOtherData
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
}