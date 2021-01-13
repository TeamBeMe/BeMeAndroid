package com.teambeme.beme.home.repository

import com.teambeme.beme.home.model.ResponseAnswer
import com.teambeme.beme.home.model.ResponseAnswers
import com.teambeme.beme.home.model.ResponseModifyData

interface HomeRepository {
    suspend fun modifyPublic(answerId: Int, publicFlag: Int): ResponseModifyData
    suspend fun getAnswers(page: Int): ResponseAnswers
    suspend fun getNewAnswer(): ResponseAnswer
    suspend fun changeQuestion(answerId: Int): ResponseAnswer
    suspend fun deleteAnswer(answerId: Int): ResponseModifyData
}