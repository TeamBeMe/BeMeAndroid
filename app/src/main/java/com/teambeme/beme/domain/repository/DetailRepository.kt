package com.teambeme.beme.domain.repository

import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

interface DetailRepository {
    fun getDetail(answerId: Int): Call<ResponseDetail>
    fun deleteReply(commentId: Int): Call<ResponseDeleteReply>
    fun postReply(
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply>

    fun putReply(commentId: Int, content: String): Call<ResponsePutReply>
    fun putScrap(answerId: Int): Call<ResponseScrap>
    fun deleteAnswer(answerId: Int): Call<ResponseDeleteAnswer>
}
