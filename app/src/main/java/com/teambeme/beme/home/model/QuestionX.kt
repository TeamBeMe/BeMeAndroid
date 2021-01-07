package com.teambeme.beme.home.model

import com.google.gson.annotations.SerializedName

data class QuestionX(
    @SerializedName("Category")
    val category: Category,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)