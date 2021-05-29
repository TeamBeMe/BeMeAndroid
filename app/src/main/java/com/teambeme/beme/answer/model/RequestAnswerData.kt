package com.teambeme.beme.answer.model

import com.google.gson.annotations.SerializedName

data class RequestAnswerData(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("is_comment_blocked")
    val isCommentBlocked: Boolean?,
    @SerializedName("is_public")
    val isPublic: Boolean?
)
