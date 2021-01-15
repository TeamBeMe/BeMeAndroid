package com.teambeme.beme.signup.repository

import com.teambeme.beme.data.remote.datasource.SignUpDataSource
import com.teambeme.beme.signup.model.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun signUp(
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    ): ResponseSignUp {
        return signUpDataSource.signUp(body, image)
    }

    override suspend fun nickNameDoubleCheck(nickName: String) =
        signUpDataSource.nickDoubleCheck(nickName)
}