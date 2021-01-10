package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.OtherService
import com.teambeme.beme.otherpage.model.*
import retrofit2.Call

class OtherPageDataSourceImpl(private val service: OtherService) :
    OtherPageDataSource {
    override fun getProfileAnswer(token: String, userId: Int, page: Int): Call<ResponseOtherData> =
        service.getProfileAnswer(token, userId, page)

    override fun getOtherInfo(token: String, userId: Int): Call<ResponseOtherInfo> =
        service.getOtherInfo(token, userId)

    override fun putScrap(token: String, answerId: Int): Call<ResponseScrap> =
        service.putScrap(token, answerId)

    override fun putFollow(token: String, userId: Int): Call<ResponseFollow> =
        service.putFollow(token, RequestFollow(userId))
}