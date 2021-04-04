package com.teambeme.beme.data.repository

import com.teambeme.beme.idsearchfollowing.model.*

interface IdSearchRepository {
    suspend fun idSearch(query: String, range: String): ResponseIdSearchData
    suspend fun getRecentSearchRecord(): ResponseRecentSearchRecord
    suspend fun deleteRecentSearchRecord(searchedId: Int): ResponseDeleteRecentSearchRecord
    suspend fun putFollowAndFollowing(userId: Int): ResponseFollowAndFollowing
}