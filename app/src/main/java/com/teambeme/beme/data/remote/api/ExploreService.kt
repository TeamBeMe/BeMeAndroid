package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
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
}