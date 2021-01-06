package com.teambeme.beme.home.model

data class Question(
    val isLock: Boolean,
    val isPast: Boolean,
    val info: String,
    val date: String,
    val title: String,
    val content: String,
    val id: Int
)
