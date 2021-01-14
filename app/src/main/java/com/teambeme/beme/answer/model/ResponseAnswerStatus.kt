package com.teambeme.beme.answer.model

import com.google.gson.annotations.SerializedName

data class ResponseAnswerStatus(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)