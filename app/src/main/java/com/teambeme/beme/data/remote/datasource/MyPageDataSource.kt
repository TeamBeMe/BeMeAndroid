package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.mypage.model.*
import okhttp3.MultipartBody
import retrofit2.Call

interface MyPageDataSource {
    fun putProfile(
        token: String,
        file: MultipartBody.Part?

    ): Call<ResponseProfile>

    fun getMyProfile(token: String): Call<ResponseMyProfile>

    fun getMyAnswer(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer>

    fun getMyScrap(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap>

    fun putPublic(token: String, answerId: Int, publicFlag: Int): Call<ResponsePublic>
}