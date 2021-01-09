package com.teambeme.beme.otherpage.model
import com.google.gson.annotations.SerializedName

data class ResponseOtherData(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)