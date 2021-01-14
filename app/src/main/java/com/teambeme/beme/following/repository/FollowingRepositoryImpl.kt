package com.teambeme.beme.following.repository

import com.teambeme.beme.data.remote.datasource.FollowingDataSource
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.RequestFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import retrofit2.Call

class FollowingRepositoryImpl(private val followingDataSource: FollowingDataSource) : FollowingRepository {
    override fun getFollowingAnswers(
        page: Int
    ): Call<ResponseExplorationQuestions> {
        return followingDataSource.getFollowingAnswers(page)
    }

    override fun getFollowingFollowerList(): Call<ResponseFollowingList> {
        return followingDataSource.getFollowingFollowerList()
    }

    override fun getSearchMyFollowingFollower(
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId> {
        return followingDataSource.getSearchMyFollowingFollower(query, range)
    }

    override fun putFollow(body: RequestFollowingFollow): Call<ResponseFollowingFollow> {
        return followingDataSource.putFollow(body)
    }

    override fun deleteFollow(userId: Int): Call<ResponseFollowingFollow> {
        return followingDataSource.deleteFollower(userId)
    }
}