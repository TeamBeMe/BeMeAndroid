package com.teambeme.beme.explore.repository

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import com.teambeme.beme.explore.model.ResponseExploraionAnswers
import retrofit2.Call

class ExploreRepositoryImpl(private val exploreDataSource: ExploreDataSource) : ExploreRepository {
    override fun getExplorationAnother(token: String): Call<ResponseExploraionAnswers> =
        exploreDataSource.getExplorationAnother(token)
}