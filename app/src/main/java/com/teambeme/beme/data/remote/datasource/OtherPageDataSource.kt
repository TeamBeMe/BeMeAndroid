package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.otherpage.model.ResponseOtherData
import retrofit2.Call

interface OtherPageDataSource {
    fun getProfileAnswer(token: String, user_id: Int, page: Int): Call<ResponseOtherData>
}