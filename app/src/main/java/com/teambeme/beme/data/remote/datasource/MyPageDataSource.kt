package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.mypage.model.*
import okhttp3.MultipartBody
import retrofit2.Call

interface MyPageDataSource {
    fun putProfile(
        file: MultipartBody.Part?

    ): Call<ResponseProfile>

    fun getMyProfile(): Call<ResponseMyProfile>

    fun getMyAnswer(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer>

    fun getMyScrap(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap>

    fun putPublic(answerId: Int, publicFlag: Int): Call<ResponsePublic>
}