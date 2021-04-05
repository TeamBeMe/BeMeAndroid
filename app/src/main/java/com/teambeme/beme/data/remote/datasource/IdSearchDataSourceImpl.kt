package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.IdSearchService
import com.teambeme.beme.idsearchfollowing.model.*
import javax.inject.Inject

class IdSearchDataSourceImpl @Inject constructor(
    private val service: IdSearchService
) : IdSearchDataSource {
    override suspend fun idSearch(query: String, range: String): ResponseIdSearchData =
        service.idSearch(query, range)

    override suspend fun getRecentSearchRecord(): ResponseRecentSearchRecord =
        service.getRecentSearchRecord()

    override suspend fun deleteRecentSearchRecord(searchedId: Int): ResponseDeleteRecentSearchRecord =
        service.deleteRecentSearchRecord(searchedId)

    override suspend fun putFollowAndFollowing(body: RequestFollowAndFollowing): ResponseFollowAndFollowing =
        service.putFollowAndFollowing(body)
}