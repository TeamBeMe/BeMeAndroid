package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.IdSearchService
import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import retrofit2.Call

class IdSearchDataSourceImpl(private val service: IdSearchService) :
    IdSearchDataSource {
    override fun idSearch(token: String, query: String, range: String?): Call<ResponseIdSearchData> = service.idSearch(token, query, range)

    override fun getRecentSearchRecord(token: String): Call<ResponseRecentSearchRecord> = service.getRecentSearchRecord(token)

    override fun deleteRecentSearchRecord(token: String, searchedId: Int): Call<ResponseDeleteRecentSearchRecord> =
        service.deleteRecentSearchRecord(token, searchedId)
}