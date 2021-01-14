package com.teambeme.beme.idsearchfollowing.model

import com.google.gson.annotations.SerializedName

data class ResponseFollowAndFollowing(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)