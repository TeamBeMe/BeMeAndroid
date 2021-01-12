package com.teambeme.beme.otherpage.repository

import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherData
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

interface OtherPageRepository {
    fun getProfileAnswer(token: String, userId: Int, page: Int): Call<ResponseOtherData>
    fun getOtherInfo(token: String, userId: Int): Call<ResponseOtherInfo>
    fun putScrap(token: String, answerId: Int): Call<ResponseScrap>
    fun putFollow(token: String, userId: Int): Call<ResponseFollow>
}