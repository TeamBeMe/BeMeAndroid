package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.MyPageService
import com.teambeme.beme.mypage.model.ResponseProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class MyPageDataSourceImpl(private val service: MyPageService) : MyPageDataSource {
    override fun putProfile(
        profileImg: RequestBody,
        part: MultipartBody.Part,
        token: String
    ): Call<ResponseProfile> {
        return service.putProfile(profileImg, part, token)
    }
}