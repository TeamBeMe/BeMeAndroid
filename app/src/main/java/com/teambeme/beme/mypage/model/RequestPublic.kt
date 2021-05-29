package com.teambeme.beme.mypage.model

import com.google.gson.annotations.SerializedName

data class RequestPublic(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("public_flag")
    val publicFlag: Int
)
