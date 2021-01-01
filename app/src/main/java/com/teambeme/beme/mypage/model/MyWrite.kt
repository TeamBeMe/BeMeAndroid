package com.teambeme.beme.mypage.model

data class MyWrite(
    val question: String,
    val categori: String,
    val time: String,
    val content: String,
    val isSecret: Boolean
)