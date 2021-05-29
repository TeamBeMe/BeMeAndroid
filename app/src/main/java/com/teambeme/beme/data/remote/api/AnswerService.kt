package com.teambeme.beme.data.remote.api

import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.model.ResponseAnswerStatus
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface AnswerService {
    @Headers("Content-Type:application/json")
    @POST("answers")
    suspend fun registerAnswer(
        @Body body: RequestAnswerData
    ): ResponseAnswerStatus

    @Headers("Content-Type:application/json")
    @PUT("answers")
    suspend fun modifyAnswer(
        @Body body: RequestAnswerData
    ): ResponseAnswerStatus
}
