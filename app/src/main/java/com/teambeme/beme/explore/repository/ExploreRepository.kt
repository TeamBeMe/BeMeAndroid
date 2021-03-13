package com.teambeme.beme.explore.repository

import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call

interface ExploreRepository {

    fun getExplorationOtherQuestions(
        page: Int,
        category: Int?
    ): Call<ResponseExplorationQuestions>

    fun getExplorationSameQuestionOtherAnswers(
        questionId: Int,
        page: Int
    ): Call<ResponseExplorationQuestions>

    fun getQuestionForFirstAnswer(): Call<ResponseExplorationQuestionForFirstAnswer>

    fun putScrap(
        answerId: Int
    ): Call<ResponseExplorationScrap>
}