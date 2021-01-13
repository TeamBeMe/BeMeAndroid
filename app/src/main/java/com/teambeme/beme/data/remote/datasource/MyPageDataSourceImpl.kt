package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.MyPageService
import com.teambeme.beme.mypage.model.*
import okhttp3.MultipartBody
import retrofit2.Call

class MyPageDataSourceImpl(private val service: MyPageService) : MyPageDataSource {
    override fun putProfile(
        token: String,
        file: MultipartBody.Part?
    ): Call<ResponseProfile> {
        return service.putProfile(token, file)
    }

    override fun getMyAnswer(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer> {
        return service.getMyAnswer(token, public, category, query, page)
    }

    override fun getMyProfile(token: String): Call<ResponseMyProfile> {
        return service.getMyProfile(token)
    }

    override fun getMyScrap(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap> {
        return service.getMyScrap(token, public, category, query, page)
    }

    override fun putPublic(token: String, answerId: Int, publicFlag: Int): Call<ResponsePublic> {
        return service.putPublic(token, RequestPublic(answerId, publicFlag))
    }
}