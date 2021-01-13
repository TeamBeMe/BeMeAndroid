package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface FollowingService {
    @Headers("Content-Type:application/json")
    @GET("follow/answers")
    fun getFollowingFollowerAnswers(
        @Header("token") token: String,
        @Query("page") page: Int,
        @Query("category") category: String
    ): Call<ResponseExplorationQuestions>
    @GET("follow")
    fun getFollowingFollowerList(
        @Header("token") token: String
    ): Call<ResponseFollowingList>
    @GET("users/search")
    fun getSearchMyFollowingFollower(
        @Header("token") token: String,
        @Query("query") query: String,
        @Query("range") range: String
    ): Call<ResponseFollowingSearchId>
}