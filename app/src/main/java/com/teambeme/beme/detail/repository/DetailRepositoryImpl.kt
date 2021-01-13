package com.teambeme.beme.detail.repository

import com.teambeme.beme.data.remote.datasource.DetailDataSource
import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

class DetailRepositoryImpl(private val detailDataSource: DetailDataSource) :
    DetailRepository {
    override fun getDetail(token: String, answerId: Int): Call<ResponseDetail> =
        detailDataSource.getDetail(token, answerId)

    override fun deleteReply(token: String, commentId: Int): Call<ResponseDeleteReply> =
        detailDataSource.deleteReply(token, commentId)

    override fun postReply(
        token: String,
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply> =
        detailDataSource.postReply(token, answerId, content, isPublic, parentId)

    override fun putReply(token: String, commentId: Int, content: String): Call<ResponsePutReply> =
        detailDataSource.putReply(token, commentId, content)

    override fun putScrap(token: String, answerId: Int): Call<ResponseScrap> =
        detailDataSource.putScrap(token, answerId)

    override fun deleteAnswer(token: String, answerId: Int): Call<ResponseDeleteAnswer> =
        detailDataSource.deleteAnswer(token, answerId)
}
