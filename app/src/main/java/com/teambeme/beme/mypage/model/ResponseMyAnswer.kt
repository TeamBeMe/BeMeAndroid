package com.teambeme.beme.mypage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseMyAnswer(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("answers")
        val answers: List<Answer>,
        @SerializedName("page_len")
        val pageLen: Int
    ) : Parcelable {
        @Parcelize
        data class Answer(
            @SerializedName("answer_date")
            val answerDate: String,
            @SerializedName("answer_idx")
            val answerIdx: Int,
            @SerializedName("category")
            val category: String,
            @SerializedName("category_id")
            val categoryId: Int,
            @SerializedName("comment_blocked_flag")
            val commentBlockedFlag: Boolean,
            @SerializedName("content")
            val content: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_answered")
            val isAnswered: Boolean,
            @SerializedName("is_author")
            val isAuthor: Boolean,
            @SerializedName("is_scrapped")
            val isScrapped: Boolean,
            @SerializedName("public_flag")
            var publicFlag: Boolean,
            @SerializedName("question")
            val question: String,
            @SerializedName("question_id")
            val questionId: Int,
            @SerializedName("user_id")
            val userId: Int,
            @SerializedName("user_nickname")
            val userNickname: String,
            @SerializedName("user_profile")
            val userProfile: String?
        ) : Parcelable
    }
}