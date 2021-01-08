package com.teambeme.beme.notification.model

import com.google.gson.annotations.SerializedName

data class ResponseComment(
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
        @SerializedName("answer_id")
        val answerId: Int,
        @SerializedName("content")
        val content: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("parent_id")
        val parentId: Int,
        @SerializedName("public_flag")
        val publicFlag: Boolean,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: Int
    )
}