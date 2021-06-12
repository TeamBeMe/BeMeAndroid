package com.teambeme.beme.domain.repository

import androidx.paging.PagingData
import com.teambeme.beme.presentation.home.model.Answer
import com.teambeme.beme.presentation.home.model.ResponseAnswer
import com.teambeme.beme.presentation.home.model.ResponseAnswers
import com.teambeme.beme.presentation.home.model.ResponseModifyData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun modifyPublic(answerId: Int, publicFlag: Int): ResponseModifyData
    suspend fun getAnswers(page: Int): ResponseAnswers
    suspend fun getNewAnswer(): ResponseAnswer
    suspend fun changeQuestion(answerId: Int): ResponseAnswer
    suspend fun deleteAnswer(answerId: Int): ResponseModifyData
    fun retrieveAnswerPages(): Flow<PagingData<Answer>>
}
