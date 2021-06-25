package com.teambeme.beme.data.remote.api

import com.teambeme.beme.idsearchfollowing.model.*
import retrofit2.http.*

interface IdSearchService {
    @GET("users/search")
    suspend fun idSearch(
        @Query("query") query: String,
        @Query("range") range: String
    ): ResponseIdSearchData

    @DELETE("users/search/{searchedId}")
    suspend fun deleteRecentSearchRecord(
        @Path("searchedId") searchedId: Int
    ): ResponseDeleteRecentSearchRecord

    @GET("users/search/history")
    suspend fun getRecentSearchRecord(): ResponseRecentSearchRecord

    @PUT("follow")
    suspend fun putFollowAndFollowing(
        @Body body: RequestFollowAndFollowing
    ): ResponseFollowAndFollowing
}
