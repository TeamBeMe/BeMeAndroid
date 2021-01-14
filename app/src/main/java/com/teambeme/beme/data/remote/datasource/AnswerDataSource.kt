package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.model.ResponseAnswerStatus

interface AnswerDataSource {
    suspend fun registerAnswer(body: RequestAnswerData): ResponseAnswerStatus
    suspend fun modifyAnswer(body: RequestAnswerData): ResponseAnswerStatus
}