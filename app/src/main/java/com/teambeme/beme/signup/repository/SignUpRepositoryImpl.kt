package com.teambeme.beme.signup.repository

import com.teambeme.beme.data.remote.datasource.SignUpDataSource
import com.teambeme.beme.signup.model.RequestSignUp
import com.teambeme.beme.signup.model.ResponseSignUp
import java.io.File

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun signUp(
        email: String,
        nickName: String,
        passWord: String,
        image: File?
    ): ResponseSignUp = signUpDataSource.signUp(
        RequestSignUp(email, nickName, passWord, image)
    )
}