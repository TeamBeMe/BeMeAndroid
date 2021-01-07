package com.teambeme.beme.home.model

import com.google.gson.annotations.SerializedName

data class ResponseAnswerData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)