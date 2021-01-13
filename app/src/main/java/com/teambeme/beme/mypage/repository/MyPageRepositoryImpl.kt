package com.teambeme.beme.mypage.repository

import com.teambeme.beme.data.remote.datasource.MyPageDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class MyPageRepositoryImpl(private val myDataSource: MyPageDataSource) :
    MyPageRepository {
    override fun putProfile(profileImg: RequestBody, part: MultipartBody.Part, token: String) =
        myDataSource.putProfile(profileImg, part, token)
}