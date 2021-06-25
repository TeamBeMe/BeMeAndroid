package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.following.model.*
import retrofit2.Call

interface FollowingDataSource {
    fun getFollowingAnswers(
        page: Int
    ): Call<ResponseExplorationQuestions>

    fun getFollowingFollowerList(): Call<ResponseFollowingList>

    fun getSearchMyFollowingFollower(
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId>

    fun putFollow(
        body: RequestFollowingFollow
    ): Call<ResponseFollowingFollow>

    fun deleteFollower(
        userId: Int
    ): Call<ResponseFollowingFollow>

    fun putScrap(
        answerId: Int
    ): Call<ResponseExplorationScrap>

    fun postAnswer(
        questionId: RequestFollowingAnswer
    ): Call<ResponseFollowingAnswer>
}
