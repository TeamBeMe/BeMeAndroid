package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.IdSearchService
import com.teambeme.beme.idsearchfollowing.model.*
import retrofit2.Call

class IdSearchDataSourceImpl(private val service: IdSearchService) :
    IdSearchDataSource {
    override fun idSearch(query: String, range: String): Call<ResponseIdSearchData> = service.idSearch(query, range)

    override fun getRecentSearchRecord(): Call<ResponseRecentSearchRecord> = service.getRecentSearchRecord()

    override fun deleteRecentSearchRecord(searchedId: Int): Call<ResponseDeleteRecentSearchRecord> =
        service.deleteRecentSearchRecord(searchedId)

    override fun putFollowAndFollowing(body: RequestFollowAndFollowing): Call<ResponseFollowAndFollowing> =
    service.putFollowAndFollowing(body)
}