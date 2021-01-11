package com.teambeme.beme.notification.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseNoticeData(
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
        @SerializedName("activities")
        val activities: List<Activity>,
        @SerializedName("page_len")
        val pageLen: Int
    ) : Parcelable {
        @Parcelize
        data class Activity(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("profile_img")
            val profileImg: String?,
            @SerializedName("question_title")
            val questionTitle: String?,
            @SerializedName("type")
            val type: String,
            @SerializedName("user_id")
            val userId: Int,
            @SerializedName("user_nickname")
            val userNickname: String
        ) : Parcelable
    }
}