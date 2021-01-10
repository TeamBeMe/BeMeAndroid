package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.otherpage.model.ResponseOtherData
import retrofit2.Call

interface OtherPageDataSource {
    fun getProfileAnswer(token: String, userId: Int, page: Int): Call<ResponseOtherData>
}