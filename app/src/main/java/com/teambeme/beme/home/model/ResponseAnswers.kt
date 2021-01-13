package com.teambeme.beme.home.model

import com.google.gson.annotations.SerializedName

data class ResponseAnswers(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val answers: List<Answer>
)
