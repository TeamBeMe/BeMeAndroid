package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.HomeService
import com.teambeme.beme.home.model.RequestModifyPublic
import com.teambeme.beme.home.model.ResponseAnswer
import com.teambeme.beme.home.model.ResponseAnswers
import com.teambeme.beme.home.model.ResponseModifyData

class HomeDataSourceImpl(private val homeService: HomeService) : HomeDataSource {
    override suspend fun modifyPublic(body: RequestModifyPublic): ResponseModifyData =
        homeService.modifyPublic(body)

    override suspend fun getAnswers(page: Int): ResponseAnswers = homeService.getAnswers(page)

    override suspend fun getNewAnswer(): ResponseAnswer = homeService.getNewAnswer()

    override suspend fun changeQuestion(answerId: Int): ResponseAnswer =
        homeService.changeQuestion(answerId)

    override suspend fun deleteQuestion(answerId: Int): ResponseModifyData =
        homeService.deleteQuestion(answerId)
}