package com.teambeme.beme.signup.model

import com.google.gson.annotations.SerializedName

data class ResponseNickDoubleCheck(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("nicknameExist")
        val nicknameExist: Boolean
    )
}