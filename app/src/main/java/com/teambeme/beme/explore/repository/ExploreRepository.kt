package com.teambeme.beme.explore.repository

import com.teambeme.beme.explore.model.ResponseExploraionAnsweres
import retrofit2.Call

interface ExploreRepository {
    fun getExplorationAnother(token: String): Call<ResponseExploraionAnsweres>
}