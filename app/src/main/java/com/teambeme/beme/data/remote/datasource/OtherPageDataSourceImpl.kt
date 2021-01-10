package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.OtherService
import com.teambeme.beme.otherpage.model.ResponseOtherData
import retrofit2.Call

class OtherPageDataSourceImpl(private val service: OtherService) :
    OtherPageDataSource {
    override fun getProfileAnswer(token: String, userId: Int, page: Int): Call<ResponseOtherData> =
        service.getProfileAnswer(token, userId, page)
}