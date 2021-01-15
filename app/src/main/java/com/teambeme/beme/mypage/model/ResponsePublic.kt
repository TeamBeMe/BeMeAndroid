package com.teambeme.beme.mypage.model

import com.google.gson.annotations.SerializedName

data class ResponsePublic(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)