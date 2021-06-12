package com.teambeme.beme.data.remote.api

import com.teambeme.beme.presentation.home.model.RequestModifyPublic
import com.teambeme.beme.presentation.home.model.ResponseAnswer
import com.teambeme.beme.presentation.home.model.ResponseAnswers
import com.teambeme.beme.presentation.home.model.ResponseModifyData
import retrofit2.Call
import retrofit2.http.*

interface HomeService {
    @Headers("Content-Type:application/json")
    @PUT("home/public")
    suspend fun modifyPublic(
        @Body body: RequestModifyPublic
    ): ResponseModifyData

    @Headers("Content-Type:application/json")
    @GET("home/all/{page}")
    suspend fun getAnswers(
        @Path("page") page: Int
    ): ResponseAnswers

    @Headers("Content-Type:application/json")
    @GET("home/all/{page}")
    fun getAnswerPages(
        @Path("page") page: Int
    ): Call<ResponseAnswers>

    @Headers("Content-Type:application/json")
    @GET("home")
    suspend fun getNewAnswer(): ResponseAnswer

    @Headers("Content-Type:application/json")
    @GET("home/{answerId}")
    suspend fun changeQuestion(
        @Path("answerId") answerId: Int
    ): ResponseAnswer

    @Headers("Content-Type:application/json")
    @DELETE("home/{answerId}")
    suspend fun deleteAnswer(
        @Path("answerId") answerId: Int
    ): ResponseModifyData
}
