package com.teambeme.beme.idsearchfollowing.model

import com.google.gson.annotations.SerializedName

data class RequestFollowAndFollowing(
    @SerializedName("user_id")
    val userId: Int
)
