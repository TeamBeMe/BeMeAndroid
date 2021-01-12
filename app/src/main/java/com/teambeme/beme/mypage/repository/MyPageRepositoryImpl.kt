package com.teambeme.beme.mypage.repository

import com.teambeme.beme.data.remote.datasource.MyPageDataSource
import com.teambeme.beme.data.remote.datasource.OtherPageDataSource
import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import com.teambeme.beme.otherpage.repository.OtherPageRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class MyPageRepositoryImpl(private val myDataSource: MyPageDataSource) :
    MyPageRepository {
    override fun putProfile(profileImg: RequestBody, part: MultipartBody.Part, token: String) =
        myDataSource.putProfile(profileImg,part,token)

}