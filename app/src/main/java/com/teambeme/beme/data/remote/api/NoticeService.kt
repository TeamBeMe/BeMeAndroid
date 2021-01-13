package com.teambeme.beme.data.remote.api

import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call
import retrofit2.http.*

interface NoticeService {

    @Headers("Content-type: application/json")
    @GET("users/activities")
    fun notice(
        @Header("token") token: String,
        @Query("page") page: Int?
    ): Call<ResponseNoticeData>
}
