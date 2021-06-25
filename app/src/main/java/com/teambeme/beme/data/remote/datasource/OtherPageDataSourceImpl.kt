package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.OtherService
import com.teambeme.beme.otherpage.model.*
import javax.inject.Inject
import retrofit2.Call

class OtherPageDataSourceImpl @Inject constructor(
    private val service: OtherService
) : OtherPageDataSource {
    override fun getProfileAnswer(userId: Int, page: Int): Call<ResponseOtherData> =
        service.getProfileAnswer(userId, page)

    override fun getOtherInfo(userId: Int): Call<ResponseOtherInfo> =
        service.getOtherInfo(userId)

    override fun putScrap(answerId: Int): Call<ResponseScrap> =
        service.putScrap(answerId)

    override fun putFollow(userId: Int): Call<ResponseFollow> =
        service.putFollow(RequestFollow(userId))
}
