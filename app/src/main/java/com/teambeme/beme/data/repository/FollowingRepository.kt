package com.teambeme.beme.data.repository

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.following.model.*
import retrofit2.Call

interface FollowingRepository {
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

    fun deleteFollow(userId: Int): Call<ResponseFollowingFollow>

    fun putScrap(
        answerId: Int
    ): Call<ResponseExplorationScrap>

    fun postAnswer(
        questionId: RequestFollowingAnswer
    ): Call<ResponseFollowingAnswer>
}
