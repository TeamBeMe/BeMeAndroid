package com.teambeme.beme.explore.model

import com.google.gson.annotations.SerializedName

data class ResponseExploraionAnsweres(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("comment_count")
        val commentCount: Int,
        @SerializedName("content")
        val content: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("Question.id")
        val questionId: Int,
        @SerializedName("Question.title")
        val questionTitle: String
    )
}