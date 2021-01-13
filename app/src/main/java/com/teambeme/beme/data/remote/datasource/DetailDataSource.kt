package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

interface DetailDataSource {
    fun getDetail(token: String, answerId: Int): Call<ResponseDetail>
    fun deleteReply(token: String, commentId: Int): Call<ResponseDeleteReply>
    fun postReply(
        token: String,
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply>

    fun putReply(token: String, commentId: Int, content: String): Call<ResponsePutReply>
    fun putScrap(token: String, answerId: Int): Call<ResponseScrap>
    fun deleteAnswer(token: String, answerId: Int): Call<ResponseDeleteAnswer>
}