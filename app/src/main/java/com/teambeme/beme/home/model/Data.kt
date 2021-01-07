package com.teambeme.beme.home.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("answer_date")
    val answerDate: String?,
    @SerializedName("answer_idx")
    val answerIdx: Int?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Question")
    val question: QuestionX
)