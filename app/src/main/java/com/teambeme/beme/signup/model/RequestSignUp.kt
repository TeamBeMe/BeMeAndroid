package com.teambeme.beme.signup.model

import com.google.gson.annotations.SerializedName

data class RequestSignUp(
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickName: String,
    @SerializedName("password")
    val passWord: String
)
