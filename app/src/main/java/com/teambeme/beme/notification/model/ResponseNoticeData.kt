package com.teambeme.beme.notification.model

import com.google.gson.annotations.SerializedName

data class ResponseNoticeData(
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
        @SerializedName("activities")
        val activities: List<Activity>,
        @SerializedName("page_len")
        val pageLen: Int
    ) {
        data class Activity(
            @SerializedName("answer_id")
            val answerId: Int?,
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("profile_img")
            val profileImg: String,
            @SerializedName("question_title")
            val questionTitle: String?,
            @SerializedName("type")
            val type: String,
            @SerializedName("user_id")
            val userId: Int,
            @SerializedName("user_nickname")
            val userNickname: String
        )
    }
}