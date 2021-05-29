package com.teambeme.beme.main.model

import com.google.gson.annotations.SerializedName

data class ResponseFbTokenRegister(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
