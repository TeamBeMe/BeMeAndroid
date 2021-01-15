package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationMinds
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call
import retrofit2.http.*

interface ExploreService {
    @Headers("Content-Type:application/json")
    @GET("exploration/another")
    fun getExplorationAnother(): Call<ResponseExplorationMinds>

    @GET("exploration")
    fun getExplorationOtherQuestions(
        @Query("page") page: Int,
        @Query("category") category: Int?,
        @Query("sorting") sorting: String
    ): Call<ResponseExplorationQuestions>

    @GET("exploration/{questionId}")
    fun getExplorationSameQuestionOtherAnswers(
        @Path("questionId") questionId: Int,
        @Query("page") page: Int,
        @Query("sorting") sorting: String
    ): Call<ResponseExplorationQuestions>

    @GET("exploration/answer")
    fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer>

    @PUT("exploration/{answerId}")
    fun putScrap(
        @Path("answerId") answerId: Int
    ): Call<ResponseExplorationScrap>
}