package com.teambeme.beme.explore.repository

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import com.teambeme.beme.explore.model.ResponseExplorationMinds
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call

class ExploreRepositoryImpl(private val exploreDataSource: ExploreDataSource) : ExploreRepository {
    override fun getExplorationAnother(token: String): Call<ResponseExplorationMinds> =
        exploreDataSource.getExplorationAnother(token)

    override fun getExplorationOtherQuestions(
        token: String,
        page: Int,
        category: Int?,
        sorting: String
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationOtherQuestions(token, page, category, sorting)
    }

    override fun getExplorationSameQuestionOtherAnswers(
        token: String,
        questionId: Int,
        page: Int,
        sorting: String
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationSameQuestionOtherAnswers(
            token,
            questionId,
            page,
            sorting
        )
    }

    override fun getQuestionForFirstAnswer(token: String): Call<ResponseExplorationQuestionForFirstAnswer> {
        return exploreDataSource.getQuestionForFirstAnswer(token)
    }

    override fun putScrap(token: String, answerId: Int): Call<ResponseExplorationScrap> {
        return exploreDataSource.putScrap(token, answerId)
    }
}