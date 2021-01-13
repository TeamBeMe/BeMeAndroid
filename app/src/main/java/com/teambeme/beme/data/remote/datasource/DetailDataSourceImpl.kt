package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.DetailService
import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

class DetailDataSourceImpl(private val service: DetailService) : DetailDataSource {
    override fun getDetail(token: String, answerId: Int): Call<ResponseDetail> {
        return service.getDetail(token, answerId)
    }

    override fun deleteReply(token: String, commentId: Int): Call<ResponseDeleteReply> {
        return service.deleteReply(token, commentId)
    }

    override fun postReply(
        token: String,
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply> {
        return service.postReply(token, RequestPostReply(answerId, content, isPublic, parentId))
    }

    override fun putReply(token: String, commentId: Int, content: String): Call<ResponsePutReply> {
        return service.putReply(token, RequestPutReply(commentId, content))
    }

    override fun putScrap(token: String, answerId: Int): Call<ResponseScrap> =
        service.putScrap(token, answerId)

    override fun deleteAnswer(token: String, answerId: Int): Call<ResponseDeleteAnswer> {
        return service.deleteAnswer(token, answerId)
    }
}