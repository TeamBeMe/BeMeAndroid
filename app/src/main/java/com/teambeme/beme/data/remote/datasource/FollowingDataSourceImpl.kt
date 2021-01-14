package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.FollowingService
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call

class FollowingDataSourceImpl(private val service: FollowingService) : FollowingDataSource {
    override fun getFollowingFollowerAnswers(
        token: String,
        page: Int,
        category: String
    ): Call<ResponseExplorationQuestions> {
        return service.getFollowingFollowerAnswers(token, page, category)
    }

    override fun getFollowingFollowerList(token: String): Call<ResponseFollowingList> {
        return service.getFollowingFollowerList(token)
    }

    override fun getSearchMyFollowingFollower(
        token: String,
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId> {
        return service.getSearchMyFollowingFollower(token, query, range)
    }
}