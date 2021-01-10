package com.teambeme.beme.explore.repository

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import com.teambeme.beme.explore.model.ResponseExploraionAnsweres
import retrofit2.Call

class ExploreRepositoryImpl(private val exploreDataSource: ExploreDataSource) : ExploreRepository {
    override fun getExplorationAnother(token: String): Call<ResponseExploraionAnsweres> =
        exploreDataSource.getExplorationAnother(token)
}