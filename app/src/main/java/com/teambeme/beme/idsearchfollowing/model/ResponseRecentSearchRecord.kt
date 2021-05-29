package com.teambeme.beme.idsearchfollowing.model

import com.google.gson.annotations.SerializedName

data class ResponseRecentSearchRecord(
    @SerializedName("data")
    val `data`: List<Data>?,
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
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("profile_img")
        val profileImg: String
    )
}
