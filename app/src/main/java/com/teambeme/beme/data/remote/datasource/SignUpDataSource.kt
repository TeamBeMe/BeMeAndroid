package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SignUpDataSource {
    suspend fun signUp(
        body: HashMap<String, RequestBody>,
        part: MultipartBody.Part?
    ): ResponseSignUp

    suspend fun nickDoubleCheck(nickName: String): ResponseNickDoubleCheck
}
