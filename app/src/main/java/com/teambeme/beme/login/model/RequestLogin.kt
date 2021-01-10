package com.teambeme.beme.login.model

import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("password")
    val password: String
)