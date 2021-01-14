package com.teambeme.beme.idsearchfollowing.repository

import com.teambeme.beme.idsearchfollowing.model.*
import retrofit2.Call

interface IdSearchRepository {
    fun idSearch(query: String, range: String?): Call<ResponseIdSearchData>
    fun getRecentSearchRecord(): Call<ResponseRecentSearchRecord>
    fun deleteRecentSearchRecord(searchedId: Int): Call<ResponseDeleteRecentSearchRecord>
    fun putFollowAndFollowing(
        body: RequestFollowAndFollowing
    ): Call<ResponseFollowAndFollowing>
}