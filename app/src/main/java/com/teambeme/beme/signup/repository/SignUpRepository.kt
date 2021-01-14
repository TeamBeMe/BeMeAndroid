package com.teambeme.beme.signup.repository

import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SignUpRepository {
    suspend fun signUp(
        body: HashMap<String, RequestBody>,
        image: MultipartBody.Part?
    ): ResponseSignUp

    suspend fun nickNameDoubleCheck(nickName: String): ResponseNickDoubleCheck
}