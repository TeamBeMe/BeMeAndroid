package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.IdSearchDataSource
import com.teambeme.beme.idsearchfollowing.model.RequestFollowAndFollowing
import javax.inject.Inject

class IdSearchRepositoryImpl @Inject constructor(
    private val idSearchDataSource: IdSearchDataSource
) : IdSearchRepository {
    override suspend fun idSearch(query: String, range: String) =
        idSearchDataSource.idSearch(query, range)

    override suspend fun getRecentSearchRecord() = idSearchDataSource.getRecentSearchRecord()

    override suspend fun deleteRecentSearchRecord(searchedId: Int) =
        idSearchDataSource.deleteRecentSearchRecord(searchedId)

    override suspend fun putFollowAndFollowing(userId: Int) =
        idSearchDataSource.putFollowAndFollowing(RequestFollowAndFollowing(userId))
}