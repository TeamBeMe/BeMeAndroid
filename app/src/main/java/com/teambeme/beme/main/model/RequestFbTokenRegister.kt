package com.teambeme.beme.main.model

import com.google.gson.annotations.SerializedName

data class RequestFbTokenRegister(
    @SerializedName("fb_token")
    val fbToken: String
)