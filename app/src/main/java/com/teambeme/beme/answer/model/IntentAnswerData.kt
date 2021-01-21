package com.teambeme.beme.answer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IntentAnswerData(
    val questionId: Int,
    val answerId: Int,
    val title: String,
    val category: String,
    val categoryIdx: Int?,
    val createdAt: String,
    val content: String = "",
    val isPublic: Boolean = false,
    val isCommentBlocked: Boolean = true
) : Parcelable
