package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ExploreService {
    @Headers("Content-Type:application/json")
    @GET("exploration/another")
    fun getExplorationAnother(
        @Header("token") token: String
    ): Call<ResponseExplorationAnswers>
}