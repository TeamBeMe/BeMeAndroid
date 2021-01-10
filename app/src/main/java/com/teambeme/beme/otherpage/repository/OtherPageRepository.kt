package com.teambeme.beme.otherpage.repository

import com.teambeme.beme.otherpage.model.ResponseOtherData
import retrofit2.Call

interface OtherPageRepository {
    fun getProfileAnswer(token: String, user_id: Int, page: Int): Call<ResponseOtherData>
}