package com.teambeme.beme.following.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseFollowingList(
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
        @SerializedName("followees")
        val followees: List<Followee>,
        @SerializedName("followers")
        val followers: List<Follower>
    ) : Parcelable {
        @Parcelize
        data class Followee(
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String,
            @SerializedName("profile_img")
            val profileImg: String
        ) : Parcelable

        @Parcelize
        data class Follower(
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String,
            @SerializedName("profile_img")
            val profileImg: String
        ) : Parcelable
    }
}
