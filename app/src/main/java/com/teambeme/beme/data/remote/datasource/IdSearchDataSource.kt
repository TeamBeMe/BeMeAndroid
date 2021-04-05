package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.idsearchfollowing.model.*

interface IdSearchDataSource {
    suspend fun idSearch(query: String, range: String): ResponseIdSearchData
    suspend fun getRecentSearchRecord(): ResponseRecentSearchRecord
    suspend fun deleteRecentSearchRecord(searchedId: Int): ResponseDeleteRecentSearchRecord
    suspend fun putFollowAndFollowing(body: RequestFollowAndFollowing): ResponseFollowAndFollowing
}