package com.teambeme.beme.otherpage.model
import com.google.gson.annotations.SerializedName

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
    val publicFlag: Boolean,
    @SerializedName("question")
    val question: String,
    @SerializedName("question_id")
    val questionId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_nickname")
    val userNickname: String,
    @SerializedName("user_profile")
    val userProfile: Any?
)