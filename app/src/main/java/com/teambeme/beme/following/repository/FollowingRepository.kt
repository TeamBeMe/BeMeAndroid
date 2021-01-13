package com.teambeme.beme.following.repository

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call

interface FollowingRepository {
    fun getFollowingAnswers(
        token: String,
        page: Int
    ): Call<ResponseExplorationQuestions>

    fun getFollowingFollowerList(
        token: String
    ): Call<ResponseFollowingList>

    fun getSearchMyFollowingFollower(
        token: String,
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId>
}