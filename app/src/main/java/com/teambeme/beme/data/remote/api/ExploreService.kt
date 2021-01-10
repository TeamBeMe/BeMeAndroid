package com.teambeme.beme.data.remote.api

import com.teambeme.beme.explore.model.ResponseExploraionAnsweres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExploreService {
    @Headers(
        "Content-Type:application/json",
        "token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMjcyMzc3LCJleHAiOjE2MTAyOTc1NzcsImlzcyI6ImJlbWUifQ.ebGvkOGMnGKli0spRneO3uAOZ31khRrPIc_Urwpu0Q0"
    )
    @GET("exploration/another")
    fun getExplorationAnother(): Call<ResponseExploraionAnsweres>
}