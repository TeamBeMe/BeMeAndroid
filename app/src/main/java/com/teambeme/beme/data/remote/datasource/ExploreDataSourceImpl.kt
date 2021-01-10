package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.ExploreService
import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import retrofit2.Call

class ExploreDataSourceImpl(private val service: ExploreService) : ExploreDataSource {
    override fun getExplorationAnother(token: String): Call<ResponseExplorationAnswers> {
        return service.getExplorationAnother(token)
    }

    override fun getExplorationOtherQuestions(
        token: String,
        page: Int,
        category: Int?,
        sorting: String
    ): Call<ResponseExplorationQuestions> {
        return service.getExplorationOtherQuestions(token, page, category, sorting)
    }
}