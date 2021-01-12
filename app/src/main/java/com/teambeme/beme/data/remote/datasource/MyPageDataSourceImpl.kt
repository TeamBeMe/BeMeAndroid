package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.MyPageService
import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.model.ResponseMyProfile
import com.teambeme.beme.mypage.model.ResponseMyScrap
import com.teambeme.beme.mypage.model.ResponseProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class MyPageDataSourceImpl(private val service: MyPageService) : MyPageDataSource {
    override fun putProfile(
        token: String,
        file: MultipartBody.Part?
    ): Call<ResponseProfile> {
        return service.putProfile( token,file)
    }

    override fun getMyAnswer(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer> {
        return service.getMyAnswer(token,public,category,query,page)
    }

    override fun getMyProfile(token: String) : Call<ResponseMyProfile> {
        return service.getMyProfile(token)
    }

    override fun getMyScrap(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap> {
        return service.getMyScrap(token,public,category,query,page)
    }
}