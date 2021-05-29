package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.ExploreService
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import javax.inject.Inject
import retrofit2.Call

class ExploreDataSourceImpl @Inject constructor(
    private val service: ExploreService
) : ExploreDataSource {
    override fun getExplorationOtherQuestions(
        page: Int,
        category: Int?
    ): Call<ResponseExplorationQuestions> {
        return service.getExplorationOtherQuestions(page, category)
    }

    override fun getExplorationSameQuestionOtherAnswers(
        questionId: Int,
        page: Int
    ): Call<ResponseExplorationQuestions> {
        return service.getExplorationSameQuestionOtherAnswers(questionId, page)
    }

    override fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer> {
        return service.getQuestionForFirstAnswer()
    }

    override fun putScrap(answerId: Int): Call<ResponseExplorationScrap> {
        return service.putScrap(answerId)
    }
}
