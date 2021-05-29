package com.teambeme.beme.following.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFollowingSearchId(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_followed")
        var isFollowed: Boolean?,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("profile_img")
        val profileImg: String
    ) : Parcelable
}
