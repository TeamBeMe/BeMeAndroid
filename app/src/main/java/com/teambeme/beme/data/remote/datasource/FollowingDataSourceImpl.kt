package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.FollowingService
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.following.model.*
import retrofit2.Call

class FollowingDataSourceImpl(private val service: FollowingService) : FollowingDataSource {
    override fun getFollowingAnswers(
        page: Int
    ): Call<ResponseExplorationQuestions> {
        return service.getFollowingAnswers(page)
    }

    override fun getFollowingFollowerList(): Call<ResponseFollowingList> {
        return service.getFollowingFollowerList()
    }

    override fun getSearchMyFollowingFollower(
        query: String,
        range: String
    ): Call<ResponseFollowingSearchId> {
        return service.getSearchMyFollowingFollower(query, range)
    }

    override fun putFollow(body: RequestFollowingFollow): Call<ResponseFollowingFollow> {
        return service.putFollow(body)
    }

    override fun deleteFollower(userId: Int): Call<ResponseFollowingFollow> {
        return service.deleteFollower(userId)
    }

    override fun putScrap(answerId: Int): Call<ResponseExplorationScrap> {
        return service.putScrap(answerId)
    }

    override fun postAnswer(questionId: RequestFollowingAnswer): Call<ResponseFollowingAnswer> {
        return service.postAnswer(questionId)
    }
}