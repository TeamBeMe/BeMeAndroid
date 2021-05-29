package com.teambeme.beme.home.model

import com.google.gson.annotations.SerializedName

data class ResponseQuestionData(
    @SerializedName("data")
    val `data`: Answer,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Answer(
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
        val question: Question,
        @SerializedName("is_today")
        val isToday: Boolean,
        @SerializedName("public_flag")
        val publicFlag: Int
    ) {
        data class Question(
            @SerializedName("Category")
            val category: Category,
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        ) {
            data class Category(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String
            )
        }
    }
}
