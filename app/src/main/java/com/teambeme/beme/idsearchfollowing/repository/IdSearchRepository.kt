package com.teambeme.beme.idsearchfollowing.repository

import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import retrofit2.Call

interface IdSearchRepository {
    fun idSearch(query: String, range: String?): Call<ResponseIdSearchData>
    fun getRecentSearchRecord(): Call<ResponseRecentSearchRecord>
    fun deleteRecentSearchRecord(searchedId: Int): Call<ResponseDeleteRecentSearchRecord>
}