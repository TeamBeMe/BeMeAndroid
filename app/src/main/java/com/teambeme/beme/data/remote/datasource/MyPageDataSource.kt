package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.model.ResponseMyProfile
import com.teambeme.beme.mypage.model.ResponseMyScrap
import com.teambeme.beme.mypage.model.ResponseProfile
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface MyPageDataSource {
    fun putProfile(
        token: String,
        file: MultipartBody.Part?

    ): Call<ResponseProfile>

    fun getMyProfile( token:String ) : Call<ResponseMyProfile>

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
}