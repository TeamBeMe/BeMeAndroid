package com.teambeme.beme.following.repository

import com.teambeme.beme.data.remote.datasource.FollowingDataSource
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call

class FollowingRepositoryImpl(private val followingDataSource: FollowingDataSource) : FollowingRepository {
    override fun getFollowingAnswers(
        token: String,
        page: Int
    ): Call<ResponseExplorationQuestions> {
        return followingDataSource.getFollowingAnswers(token, page)
    }

    override fun getFollowingFollowerList(token: String): Call<ResponseFollowingList> {
        return followingDataSource.getFollowingFollowerList(token)
    }

    override fun getSearchMyFollowingFollower(
        token: String,
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId> {
        return followingDataSource.getSearchMyFollowingFollower(token, query, range)
    }
}