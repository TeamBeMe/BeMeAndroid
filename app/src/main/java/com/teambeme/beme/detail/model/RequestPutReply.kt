package com.teambeme.beme.detail.model

import com.google.gson.annotations.SerializedName

data class RequestPutReply(
    @SerializedName("comment_id")
    val commentId: Int,
    @SerializedName("content")
    val content: String
)
