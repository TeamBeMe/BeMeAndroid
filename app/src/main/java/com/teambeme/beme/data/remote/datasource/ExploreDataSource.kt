package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call

interface ExploreDataSource {
    fun getExplorationOtherQuestions(
        page: Int,
        category: Int?,
        sorting: String
    ): Call<ResponseExplorationQuestions>

    fun getExplorationSameQuestionOtherAnswers(
        questionId: Int,
        page: Int,
        sorting: String
    ): Call<ResponseExplorationQuestions>

    fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer>

    fun putScrap(
        answerId: Int
    ): Call<ResponseExplorationScrap>
}