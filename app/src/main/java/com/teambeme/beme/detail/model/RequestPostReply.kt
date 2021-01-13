package com.teambeme.beme.detail.model

import com.google.gson.annotations.SerializedName

data class RequestPostReply(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("parent_id")
    val parentId: Int?
)