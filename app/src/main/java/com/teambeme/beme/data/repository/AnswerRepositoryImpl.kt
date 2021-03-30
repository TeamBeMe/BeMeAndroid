package com.teambeme.beme.data.repository

import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.model.ResponseAnswerStatus
import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.local.entity.AnswerData
import com.teambeme.beme.data.remote.datasource.AnswerDataSource
import javax.inject.Inject

class AnswerRepositoryImpl @Inject constructor(
    private val answerDao: AnswerDao,
    private val answerDataSource: AnswerDataSource
) : AnswerRepository {
    override suspend fun insert(answerData: AnswerData) {
        answerDao.insert(answerData)
    }

    override fun update(answerData: AnswerData) {
        answerDao.update(answerData)
    }

    override suspend fun get(key: Long): AnswerData? {
        return answerDao.get(key)
    }

    override fun delete(key: Long) {
        answerDao.delete(key)
    }

    override suspend fun registerAnswer(body: RequestAnswerData): ResponseAnswerStatus {
        return answerDataSource.registerAnswer(body)
    }

    override suspend fun modifyAnswer(body: RequestAnswerData): ResponseAnswerStatus {
        return answerDataSource.modifyAnswer(body)
    }
}