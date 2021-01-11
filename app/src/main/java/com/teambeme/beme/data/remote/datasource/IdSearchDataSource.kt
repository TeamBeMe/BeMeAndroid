package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearch
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import retrofit2.Call
import retrofit2.http.*

interface IdSearchDataSource {
    fun idSearch(token: String, query: String, range: String): Call<ResponseIdSearch>
    fun getRecentSearchRecord(token: String): Call<ResponseRecentSearchRecord>
    fun deleteRecentSearchRecord(token: String, searchedId: Int): Call<ResponseDeleteRecentSearchRecord>
}