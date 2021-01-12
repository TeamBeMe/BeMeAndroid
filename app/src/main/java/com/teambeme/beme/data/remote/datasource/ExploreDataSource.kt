package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import retrofit2.Call

interface ExploreDataSource {
    fun getExplorationAnother(token: String): Call<ResponseExplorationAnswers>

    fun getExplorationOtherQuestions(
        token: String,
        page: Int,
        category: Int?,
        sorting: String
    ): Call<ResponseExplorationQuestions>

    fun getExplorationSameQuestionOtherAnswers(
        token: String,
        questionId: Int,
        page: Int,
        sorting: String
    ): Call<ResponseExplorationQuestions>

    fun getQuestionForFirstAnswer(
        token: String
    ): Call<ResponseExplorationQuestionForFirstAnswer>

    fun putScrap(
        token: String,
        answerId: Int
    ): Call<ResponseExplorationScrap>
}