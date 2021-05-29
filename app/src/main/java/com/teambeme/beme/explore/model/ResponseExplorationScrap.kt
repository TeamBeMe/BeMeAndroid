package com.teambeme.beme.explore.model

import com.google.gson.annotations.SerializedName

data class ResponseExplorationScrap(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
