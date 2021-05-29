package com.teambeme.beme.detail.model

import com.google.gson.annotations.SerializedName

data class ResponseDeleteAnswer(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
