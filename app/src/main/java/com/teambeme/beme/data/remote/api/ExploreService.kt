package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

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
}