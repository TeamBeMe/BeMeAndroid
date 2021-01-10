package com.teambeme.beme.explore.repository

import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import retrofit2.Call

interface ExploreRepository {
    fun getExplorationAnother(token: String): Call<ResponseExplorationAnswers>
}