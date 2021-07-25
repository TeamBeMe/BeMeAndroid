package com.teambeme.beme.data.model

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T
)

data class EmptyResponse(
    val status: Int,
    val success: Boolean,
    val message: String
)
