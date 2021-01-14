package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.idsearchfollowing.model.*
import retrofit2.Call

interface IdSearchDataSource {
    fun idSearch(query: String, range: String?): Call<ResponseIdSearchData>
    fun getRecentSearchRecord(): Call<ResponseRecentSearchRecord>
    fun deleteRecentSearchRecord(searchedId: Int): Call<ResponseDeleteRecentSearchRecord>
    fun putFollowAndFollowing(
        body: RequestFollowAndFollowing
    ): Call<ResponseFollowAndFollowing>
}