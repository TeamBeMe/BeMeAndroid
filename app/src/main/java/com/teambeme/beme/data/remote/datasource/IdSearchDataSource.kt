package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import retrofit2.Call

interface IdSearchDataSource {
    fun idSearch(query: String, range: String?): Call<ResponseIdSearchData>
    fun getRecentSearchRecord(): Call<ResponseRecentSearchRecord>
    fun deleteRecentSearchRecord(searchedId: Int): Call<ResponseDeleteRecentSearchRecord>
}