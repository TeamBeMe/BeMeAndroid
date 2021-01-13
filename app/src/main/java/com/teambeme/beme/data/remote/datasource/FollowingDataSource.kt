package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call

interface FollowingDataSource {
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