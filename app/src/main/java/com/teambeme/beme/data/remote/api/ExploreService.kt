package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call
import retrofit2.http.*

interface ExploreService {
    @Headers("Content-Type:application/json")
    @GET("exploration/another")
    fun getExplorationAnother(
        @Header("token") token: String
    ): Call<ResponseExplorationAnswers>

    @GET("exploration")
    fun getExplorationOtherQuestions(
        @Header("token") token: String,
        @Query("page") page: Int,
        @Query("category") category: Int?,
        @Query("sorting") sorting: String
    ): Call<ResponseExplorationQuestions>

    @GET("exploration/{questionId}")
    fun getExplorationSameQuestionOtherAnswers(
        @Header("token") token: String,
        @Path("questionId") questionId: Int,
        @Query("page") page: Int,
        @Query("sorting") sorting: String
    ): Call<ResponseExplorationQuestions>

    @GET("exploration/answer")
    fun getQuestionForFirstAnswer(
        @Header("token") token: String
    ): Call<ResponseExplorationQuestionForFirstAnswer>

    @PUT("exploration/{answerId}")
    fun putScrap(
        @Header("token") token: String,
        @Path("answerId") answerId: Int
    ): Call<ResponseExplorationScrap>
}