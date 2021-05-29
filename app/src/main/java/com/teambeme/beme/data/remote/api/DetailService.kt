package com.teambeme.beme.data.remote.api

import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call
import retrofit2.http.*

interface DetailService {
    @Headers("Content-type: application/json")
    @GET("answers/detail/{answer_id}")
    fun getDetail(
        @Path("answer_id") answerId: Int
    ): Call<ResponseDetail>

    @Headers("Content-type: application/json")
    @DELETE("answers/comments/{comment_id}")
    fun deleteReply(
        @Path("comment_id") commentId: Int
    ): Call<ResponseDeleteReply>

    @Headers("Content-type: application/json")
    @POST("answers/comments")
    fun postReply(
        @Body body: RequestPostReply
    ): Call<ResponsePostReply>

    @Headers("Content-type: application/json")
    @PUT("answers/comments")
    fun putReply(
        @Body body: RequestPutReply
    ): Call<ResponsePutReply>

    @Headers("Content-type: application/json")
    @PUT("exploration/{answerId}")
    fun putScrap(
        @Path("answerId") answerId: Int
    ): Call<ResponseScrap>

    @Headers("Content-type: application/json")
    @DELETE("home/{answer_id}")
    fun deleteAnswer(
        @Path("answer_id") answerId: Int
    ): Call<ResponseDeleteAnswer>
}
