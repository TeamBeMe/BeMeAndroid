package com.teambeme.beme.following.model

import com.google.gson.annotations.SerializedName

data class ResponseFollowingAnswer(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("answer_idx")
        val answerIdx: Int,
        @SerializedName("category")
        val category: String,
        @SerializedName("category_id")
        val categoryId: Int,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("question")
        val question: String,
        @SerializedName("question_id")
        val questionId: Int,
        @SerializedName("user_id")
        val userId: Int
    )
}