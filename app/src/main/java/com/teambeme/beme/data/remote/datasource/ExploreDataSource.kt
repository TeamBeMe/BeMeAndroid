package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import retrofit2.Call

interface ExploreDataSource {
    fun getExplorationAnother(token: String): Call<ResponseExplorationAnswers>
    fun getExplorationOtherQuestions(
        token: String,
        page: Int,
        category: Int?,
        sorting: String
    ): Call<ResponseExplorationQuestions>
}