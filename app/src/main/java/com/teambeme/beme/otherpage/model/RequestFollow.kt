package com.teambeme.beme.otherpage.model

import com.google.gson.annotations.SerializedName

data class RequestFollow(
    @SerializedName("user_id")
    val userId: Int
)
