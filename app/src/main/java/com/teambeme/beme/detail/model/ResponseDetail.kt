package com.teambeme.beme.detail.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDetail(
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
        @SerializedName("answer_date")
        val answerDate: String,
        @SerializedName("answer_idx")
        val answerIdx: Int,
        @SerializedName("category")
        val category: String,
        @SerializedName("category_id")
        val categoryId: Int,
        @SerializedName("Comment")
        val comment: List<Comment>,
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
        var isScrapped: Boolean,
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
        val userProfile: String?
    ) : Parcelable {
        @Parcelize
        data class Comment(
            @SerializedName("answer_id")
            val answerId: Int,
            @SerializedName("Children")
            var children: List<Children>,
            @SerializedName("content")
            var content: String,
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_author")
            val isAuthor: Boolean,
            @SerializedName("is_visible")
            val isVisible: Boolean,
            @SerializedName("public_flag")
            val publicFlag: Boolean,
            @SerializedName("updatedAt")
            val updatedAt: String,
            @SerializedName("user_id")
            val userId: Int,
            @SerializedName("profile_img")
            val userProfile: String?,
            @SerializedName("user_nickname")
            val userNickname: String
        ) : Parcelable {
            @Parcelize
            data class Children(
                @SerializedName("answer_id")
                val answerId: Int,
                @SerializedName("content")
                var content: String,
                @SerializedName("createdAt")
                val createdAt: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("is_author")
                val isAuthor: Boolean,
                @SerializedName("is_visible")
                val isVisible: Boolean,
                @SerializedName("parent_id")
                val parentId: Int,
                @SerializedName("public_flag")
                val publicFlag: Boolean,
                @SerializedName("updatedAt")
                val updatedAt: String,
                @SerializedName("user_id")
                val userId: Int,
                @SerializedName("profile_img")
                val userProfile: String?,
                @SerializedName("user_nickname")
                val userNickname: String
            ) : Parcelable
        }
    }
}