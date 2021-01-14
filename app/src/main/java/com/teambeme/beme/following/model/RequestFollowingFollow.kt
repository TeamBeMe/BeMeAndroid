package com.teambeme.beme.following.model

import com.google.gson.annotations.SerializedName

data class RequestFollowingFollow(
    @SerializedName("user_id")
    val userId: Int
)