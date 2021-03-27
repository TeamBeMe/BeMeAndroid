package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.MyPageDataSource
import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.model.ResponseMyProfile
import com.teambeme.beme.mypage.model.ResponseMyScrap
import com.teambeme.beme.mypage.model.ResponsePublic
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myDataSource: MyPageDataSource
) : MyPageRepository {
    override fun putProfile(file: MultipartBody.Part?) =
        myDataSource.putProfile(file)

    override fun getMyAnswer(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyAnswer> =
        myDataSource.getMyAnswer(public, category, query, page)

    override fun getMyProfile(): Call<ResponseMyProfile> =
        myDataSource.getMyProfile()

    override fun getMyScrap(
        public: String?,
        category: Int?,
        query: String?,
        page: Int
    ): Call<ResponseMyScrap> =
        myDataSource.getMyScrap(public, category, query, page)

    override fun putPublic(answerId: Int, publicFlag: Int): Call<ResponsePublic> =
        myDataSource.putPublic(answerId, publicFlag)
}