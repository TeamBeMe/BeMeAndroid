package com.teambeme.beme.mypage.repository

import com.teambeme.beme.data.remote.datasource.MyPageDataSource
import com.teambeme.beme.data.remote.datasource.OtherPageDataSource
import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.model.ResponseMyProfile
import com.teambeme.beme.mypage.model.ResponseMyScrap
import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import com.teambeme.beme.otherpage.repository.OtherPageRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class MyPageRepositoryImpl(private val myDataSource: MyPageDataSource) :
    MyPageRepository {
    override fun putProfile(token: String,file: MultipartBody.Part?) =
        myDataSource.putProfile(token,file)

    override fun getMyAnswer(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer> =
        myDataSource.getMyAnswer(token,public,category,query,page)

    override fun getMyProfile(token: String): Call<ResponseMyProfile> =
        myDataSource.getMyProfile(token)

    override fun getMyScrap(
        token: String,
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap> =
        myDataSource.getMyScrap(token,public,category,query,page)

}