package com.teambeme.beme.presentation.home.model

import com.google.gson.annotations.SerializedName

data class RequestModifyPublic(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("public_flag")
    val publicFlag: Int
)
