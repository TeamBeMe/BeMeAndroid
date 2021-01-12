package com.teambeme.beme.signup.repository

import com.teambeme.beme.signup.model.ResponseSignUp
import java.io.File

interface SignUpRepository {
    suspend fun signUp(
        email: String,
        nickName: String,
        passWord: String,
        image: File?
    ): ResponseSignUp
}