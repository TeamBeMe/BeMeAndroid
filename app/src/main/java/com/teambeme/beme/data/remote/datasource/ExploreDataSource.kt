package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.explore.model.ResponseExploraionAnsweres
import retrofit2.Call

interface ExploreDataSource {
    fun getExplorationAnother(token: String): Call<ResponseExploraionAnsweres>
}