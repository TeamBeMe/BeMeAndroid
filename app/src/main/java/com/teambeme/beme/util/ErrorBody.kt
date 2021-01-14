package com.teambeme.beme.util

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)