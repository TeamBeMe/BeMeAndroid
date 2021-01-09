package com.teambeme.beme.otherpage.model
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("answers")
    val answers: List<Answer>,
    @SerializedName("page_len")
    val pageLen: Int
)