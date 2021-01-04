package com.teambeme.beme.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_record_table")
data class AnswerData(
    @PrimaryKey
    var questionId: Long,
    @ColumnInfo(name = "answer")
    var answer: String
)