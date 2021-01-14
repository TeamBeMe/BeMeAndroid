package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.model.ResponseAnswerStatus
import com.teambeme.beme.data.remote.api.AnswerService

class AnswerDataSourceImpl(private val answerService: AnswerService) : AnswerDataSource {
    override suspend fun registerAnswer(body: RequestAnswerData): ResponseAnswerStatus {
        return answerService.registerAnswer(body)
    }

    override suspend fun modifyAnswer(body: RequestAnswerData): ResponseAnswerStatus {
        return answerService.modifyAnswer(body)
    }
}