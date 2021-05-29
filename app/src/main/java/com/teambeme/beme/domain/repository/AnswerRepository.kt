package com.teambeme.beme.domain.repository

import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.model.ResponseAnswerStatus
import com.teambeme.beme.data.local.entity.AnswerData

interface AnswerRepository {
    suspend fun insert(answerData: AnswerData)
    fun update(answerData: AnswerData)
    suspend fun get(key: Long): AnswerData?
    fun delete(key: Long)
    suspend fun registerAnswer(body: RequestAnswerData): ResponseAnswerStatus
    suspend fun modifyAnswer(body: RequestAnswerData): ResponseAnswerStatus
}
