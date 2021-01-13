package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.SignUpService
import com.teambeme.beme.signup.model.RequestSignUp

class SignUpDataSourceImpl(private val signUpService: SignUpService) : SignUpDataSource {
    override suspend fun signUp(body: RequestSignUp) = signUpService.signUp(body)
    override suspend fun nickDoubleCheck(nickName: String) = signUpService.nickDoubleCheck(nickName)
}