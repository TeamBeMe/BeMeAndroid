package com.teambeme.beme.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Answer(
    @SerializedName("answer_date")
    val answerDate: String?,
    @SerializedName("answer_idx")
    val answerIdx: String?,
    @SerializedName("comment_blocked_flag")
    val commentBlockedFlag: Int?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_today")
    val isToday: Boolean,
    @SerializedName("public_flag")
    var publicFlag: Int,
    @SerializedName("Question.Category.id")
    val questionCategoryId: Int,
    @SerializedName("Question.Category.name")
    val questionCategoryName: String,
    @SerializedName("Question.id")
    val questionId: Int,
    @SerializedName("Question.title")
    val questionTitle: String
) : Parcelable
