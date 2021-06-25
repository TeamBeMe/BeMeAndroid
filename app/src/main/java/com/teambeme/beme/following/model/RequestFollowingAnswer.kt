package com.teambeme.beme.following.model

import com.google.gson.annotations.SerializedName

data class RequestFollowingAnswer(
    @SerializedName("question_id")
    val questionId: Int
)
