package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.following.model.*
import retrofit2.Call
import retrofit2.http.*

interface FollowingService {
    @Headers("Content-Type:application/json")
    @GET("follow/answers")
    fun getFollowingAnswers(
        @Query("page") page: Int
    ): Call<ResponseExplorationQuestions>

    @GET("follow")
    fun getFollowingFollowerList(): Call<ResponseFollowingList>

    @GET("users/search")
    fun getSearchMyFollowingFollower(
        @Query("query") query: String,
        @Query("range") range: String
    ): Call<ResponseFollowingSearchId>

    @PUT("follow")
    fun putFollow(
        @Body body: RequestFollowingFollow
    ): Call<ResponseFollowingFollow>

    @DELETE("follow/{userId}")
    fun deleteFollower(
        @Path("userId") userId: Int
    ): Call<ResponseFollowingFollow>

    @PUT("exploration/{answerId}")
    fun putScrap(
        @Path("answerId") answerId: Int
    ): Call<ResponseExplorationScrap>

    @POST("answers/question")
    fun postAnswer(
        @Body questionId: RequestFollowingAnswer
    ): Call<ResponseFollowingAnswer>
}
