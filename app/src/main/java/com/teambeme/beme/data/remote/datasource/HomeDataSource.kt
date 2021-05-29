package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.home.model.RequestModifyPublic
import com.teambeme.beme.home.model.ResponseAnswer
import com.teambeme.beme.home.model.ResponseAnswers
import com.teambeme.beme.home.model.ResponseModifyData

interface HomeDataSource {
    suspend fun modifyPublic(body: RequestModifyPublic): ResponseModifyData
    suspend fun getAnswers(page: Int): ResponseAnswers
    suspend fun getNewAnswer(): ResponseAnswer
    suspend fun changeQuestion(answerId: Int): ResponseAnswer
    suspend fun deleteAnswer(answerId: Int): ResponseModifyData
}
