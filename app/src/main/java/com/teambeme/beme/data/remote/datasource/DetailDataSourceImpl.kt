package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.DetailService
import com.teambeme.beme.detail.model.*
import com.teambeme.beme.otherpage.model.ResponseScrap
import javax.inject.Inject
import retrofit2.Call

class DetailDataSourceImpl @Inject constructor(
    private val service: DetailService
) : DetailDataSource {
    override fun getDetail(answerId: Int): Call<ResponseDetail> {
        return service.getDetail(answerId)
    }

    override fun deleteReply(commentId: Int): Call<ResponseDeleteReply> {
        return service.deleteReply(commentId)
    }

    override fun postReply(
        answerId: Int,
        content: String,
        isPublic: Boolean,
        parentId: Int?
    ): Call<ResponsePostReply> {
        return service.postReply(RequestPostReply(answerId, content, isPublic, parentId))
    }

    override fun putReply(commentId: Int, content: String): Call<ResponsePutReply> {
        return service.putReply(RequestPutReply(commentId, content))
    }

    override fun putScrap(answerId: Int): Call<ResponseScrap> =
        service.putScrap(answerId)

    override fun deleteAnswer(answerId: Int): Call<ResponseDeleteAnswer> {
        return service.deleteAnswer(answerId)
    }
}
