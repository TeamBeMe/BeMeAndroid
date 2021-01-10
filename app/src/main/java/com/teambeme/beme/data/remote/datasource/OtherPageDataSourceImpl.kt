package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.OtherService
import com.teambeme.beme.otherpage.model.ResponseOtherData
import retrofit2.Call

class OtherPageDataSourceImpl(private val service: OtherService) :
    OtherPageDataSource {
    override fun getProfileAnswer(token: String, user_id: Int, page: Int): Call<ResponseOtherData> =
        service.getProfileAnswer(token, user_id, page)
}