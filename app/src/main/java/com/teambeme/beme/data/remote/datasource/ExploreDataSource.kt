package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import retrofit2.Call

interface ExploreDataSource {
    fun getExplorationAnother(token: String): Call<ResponseExplorationAnswers>
}