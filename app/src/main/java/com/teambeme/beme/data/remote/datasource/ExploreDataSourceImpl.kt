package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.ExploreService
import com.teambeme.beme.explore.model.ResponseExploraionAnswers
import retrofit2.Call

class ExploreDataSourceImpl(private val service: ExploreService) : ExploreDataSource {
    override fun getExplorationAnother(token: String): Call<ResponseExploraionAnswers> {
        return service.getExplorationAnother(token)
    }
}