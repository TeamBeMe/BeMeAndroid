package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.DetailDataSource
import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import javax.inject.Inject
import retrofit2.Call

class DetailRepositoryImpl @Inject constructor(
    private val detailDataSource: DetailDataSource
) : DetailRepository {
    override fun getDetail(answerId: Int): Call<ResponseDetail> =
        detailDataSource.getDetail(answerId)

    override fun deleteReply(commentId: Int): Call<ResponseDeleteReply> =
        detailDataSource.deleteReply(commentId)

    override fun postReply(
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply> =
        detailDataSource.postReply(answerId, content, isPublic, parentId)

    override fun putReply(commentId: Int, content: String): Call<ResponsePutReply> =
        detailDataSource.putReply(commentId, content)

    override fun putScrap(answerId: Int): Call<ResponseScrap> =
        detailDataSource.putScrap(answerId)

    override fun deleteAnswer(answerId: Int): Call<ResponseDeleteAnswer> =
        detailDataSource.deleteAnswer(answerId)
}
