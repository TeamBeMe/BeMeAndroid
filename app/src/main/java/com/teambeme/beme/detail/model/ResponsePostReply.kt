package com.teambeme.beme.detail.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePostReply(
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
        @SerializedName("answer_id")
        val answerId: Int,
        @SerializedName("content")
        val content: String,
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
        @SerializedName("profile_img")
        val profileImg: String,
        @SerializedName("public_flag")
        val publicFlag: Boolean,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("user_nickname")
        val userNickname: String
    ) : Parcelable
}