package com.teambeme.beme.data.repository

import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherData
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

interface OtherPageRepository {
    fun getProfileAnswer(userId: Int, page: Int): Call<ResponseOtherData>
    fun getOtherInfo(userId: Int): Call<ResponseOtherInfo>
    fun putScrap(answerId: Int): Call<ResponseScrap>
    fun putFollow(userId: Int): Call<ResponseFollow>
}
