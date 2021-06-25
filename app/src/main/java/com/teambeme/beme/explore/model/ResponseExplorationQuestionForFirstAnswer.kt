package com.teambeme.beme.explore.model

import com.google.gson.annotations.SerializedName

data class ResponseExplorationQuestionForFirstAnswer(
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
        @SerializedName("answer_idx")
        val answerIdx: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("Question.Category.id")
        val questionCategoryId: Int,
        @SerializedName("Question.Category.name")
        val questionCategoryName: String,
        @SerializedName("Question.id")
        val questionId: Int,
        @SerializedName("Question.title")
        val questionTitle: String
    )
}
