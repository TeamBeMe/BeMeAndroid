package com.teambeme.beme.explore.model

data class OtherQuestionsData(
    val userId: String,
    val category: String,
    val title: String?,
    val content: String,
    val time: String,
    var isbookmarked: Boolean
)