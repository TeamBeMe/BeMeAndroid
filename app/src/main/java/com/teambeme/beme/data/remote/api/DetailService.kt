package com.teambeme.beme.data.remote.api

import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call
import retrofit2.http.*

interface DetailService {
    @Headers("Content-type: application/json")
    @GET("answers/detail/{answer_id}")
    fun getDetail(
        @Header("token") token: String,
        @Path("answer_id") answerId: Int
    ): Call<ResponseDetail>

    @Headers("Content-type: application/json")
    @DELETE("answers/comments/{comment_id}")
    fun deleteReply(
        @Header("token") token: String,
        @Path("comment_id") commentId: Int
    ): Call<ResponseDeleteReply>

    @Headers("Content-type: application/json")
    @POST("answers/comments")
    fun postReply(
        @Header("token") token: String,
        @Body body: RequestPostReply
    ): Call<ResponsePostReply>

    @Headers("Content-type: application/json")
    @PUT("answers/comments")
    fun putReply(
        @Header("token") token: String,
        @Body body: RequestPutReply
    ): Call<ResponsePutReply>

    @Headers("Content-type: application/json")
    @PUT("exploration/{answerId}")
    fun putScrap(
        @Header("token") token: String,
        @Path("answerId") answerId: Int
    ): Call<ResponseScrap>

    @Headers("Content-type: application/json")
    @DELETE("home/{answer_id}")
    fun deleteAnswer(
        @Header("token") token: String,
        @Path("answer_id") answerId: Int
    ): Call<ResponseDeleteAnswer>
}