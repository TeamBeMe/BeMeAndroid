package com.teambeme.beme.explore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseExplorationQuestionForFirstAnswer(
    @SerializedName("data")
    val `data`: Answer,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) : Parcelable {
    @Parcelize
    data class Answer(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("Question.id")
        val questionId: Int,
        @SerializedName("Question.title")
        val questionTitle: String
    ) : Parcelable
}