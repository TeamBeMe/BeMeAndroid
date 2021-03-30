package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.SignUpService
import com.teambeme.beme.signup.model.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpDataSource {
    override suspend fun signUp(
        body: HashMap<String, RequestBody>,
        part: MultipartBody.Part?
    ): ResponseSignUp {
        return signUpService.signUp(body, part)
    }

    override suspend fun nickDoubleCheck(nickName: String) = signUpService.nickDoubleCheck(nickName)
}