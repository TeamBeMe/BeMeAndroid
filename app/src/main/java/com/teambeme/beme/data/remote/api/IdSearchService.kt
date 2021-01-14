package com.teambeme.beme.data.remote.api

import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import retrofit2.Call
import retrofit2.http.*

interface IdSearchService {
    @Headers("Content-type: application/json")

    @GET("users/search")
    fun idSearch(
        @Query("query") query: String,
        @Query("range") range: String?
    ): Call<ResponseIdSearchData>

    @GET("users/search/history")
    fun getRecentSearchRecord(
    ): Call<ResponseRecentSearchRecord>

    @DELETE("users/search/{searchedId}")
    fun deleteRecentSearchRecord(
        @Path("searchedId") searchedId: Int
    ): Call<ResponseDeleteRecentSearchRecord>
}