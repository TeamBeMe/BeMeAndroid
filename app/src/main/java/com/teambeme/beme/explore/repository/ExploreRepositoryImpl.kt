package com.teambeme.beme.explore.repository

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call

class ExploreRepositoryImpl(private val exploreDataSource: ExploreDataSource) : ExploreRepository {
    override fun getExplorationOtherQuestions(
        page: Int,
        category: Int?
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationOtherQuestions(page, category)
    }

    override fun getExplorationSameQuestionOtherAnswers(
        questionId: Int,
        page: Int,
        sorting: String
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationSameQuestionOtherAnswers(
            questionId,
            page,
            sorting
        )
    }

    override fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer> {
        return exploreDataSource.getQuestionForFirstAnswer()
    }

    override fun putScrap(answerId: Int): Call<ResponseExplorationScrap> {
        return exploreDataSource.putScrap(answerId)
    }
}