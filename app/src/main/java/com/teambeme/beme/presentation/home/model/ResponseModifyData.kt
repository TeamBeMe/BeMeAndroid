package com.teambeme.beme.presentation.home.model

import com.google.gson.annotations.SerializedName

data class ResponseModifyData(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
