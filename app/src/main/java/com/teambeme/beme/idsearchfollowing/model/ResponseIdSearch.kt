package com.teambeme.beme.idsearchfollowing.model

import com.google.gson.annotations.SerializedName

data class ResponseIdSearch(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_followed")
        val isFollowed: Boolean,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("profile_img")
        val profileImg: String
    )
}