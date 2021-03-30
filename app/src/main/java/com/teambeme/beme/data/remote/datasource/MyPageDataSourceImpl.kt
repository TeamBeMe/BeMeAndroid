package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.MyPageService
import com.teambeme.beme.mypage.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val service: MyPageService
) : MyPageDataSource {
    override fun putProfile(
        file: MultipartBody.Part?
    ): Call<ResponseProfile> {
        return service.putProfile(file)
    }

    override fun getMyAnswer(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer> {
        return service.getMyAnswer(public, category, query, page)
    }

    override fun getMyProfile(): Call<ResponseMyProfile> {
        return service.getMyProfile()
    }

    override fun getMyScrap(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap> {
        return service.getMyScrap(public, category, query, page)
    }

    override fun putPublic(answerId: Int, publicFlag: Int): Call<ResponsePublic> {
        return service.putPublic(RequestPublic(answerId, publicFlag))
    }
}