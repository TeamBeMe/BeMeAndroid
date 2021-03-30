package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.HomeService
import com.teambeme.beme.home.model.RequestModifyPublic
import com.teambeme.beme.home.model.ResponseAnswer
import com.teambeme.beme.home.model.ResponseAnswers
import com.teambeme.beme.home.model.ResponseModifyData
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeDataSource {
    override suspend fun modifyPublic(body: RequestModifyPublic): ResponseModifyData =
        homeService.modifyPublic(body)

    override suspend fun getAnswers(page: Int): ResponseAnswers = homeService.getAnswers(page)

    override suspend fun getNewAnswer(): ResponseAnswer = homeService.getNewAnswer()

    override suspend fun changeQuestion(answerId: Int): ResponseAnswer =
        homeService.changeQuestion(answerId)

    override suspend fun deleteAnswer(answerId: Int): ResponseModifyData =
        homeService.deleteAnswer(answerId)
}