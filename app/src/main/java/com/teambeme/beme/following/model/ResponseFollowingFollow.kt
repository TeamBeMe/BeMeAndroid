package com.teambeme.beme.following.model

import com.google.gson.annotations.SerializedName

data class ResponseFollowingFollow(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)