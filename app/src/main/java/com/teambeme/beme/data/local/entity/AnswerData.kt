package com.teambeme.beme.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_record_table")
data class AnswerData(
    @PrimaryKey
    var questionId: Long,
    @ColumnInfo(name = "answerId")
    var answerId: Long,
    @ColumnInfo(name = "answer")
    var answer: String = "",
    @ColumnInfo(name = "isCommentBlocked")
    var isCommentBlocked: Boolean = true,
    @ColumnInfo(name = "isPublic")
    var isPublic: Boolean,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "categoryIdx")
    var categoryIdx: Int = 0,
    @ColumnInfo(name = "createdAt")
    var createdAt: String
)