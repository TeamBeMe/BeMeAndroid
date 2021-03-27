package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val exploreDataSource: ExploreDataSource
) : ExploreRepository {
    override fun getExplorationOtherQuestions(
        page: Int,
        category: Int?
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationOtherQuestions(page, category)
    }

    override fun getExplorationSameQuestionOtherAnswers(
        questionId: Int,
        page: Int
    ): Call<ResponseExplorationQuestions> {
        return exploreDataSource.getExplorationSameQuestionOtherAnswers(
            questionId,
            page
        )
    }

    override fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer> {
        return exploreDataSource.getQuestionForFirstAnswer()
    }

    override fun putScrap(answerId: Int): Call<ResponseExplorationScrap> {
        return exploreDataSource.putScrap(answerId)
    }
}