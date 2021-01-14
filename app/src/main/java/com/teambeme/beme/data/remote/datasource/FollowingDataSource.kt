package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.RequestFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
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
}