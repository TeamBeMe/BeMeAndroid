package com.teambeme.beme.login.repository

import com.teambeme.beme.login.model.ResponseLogin

interface LoginRepository {
    fun login(nickname: String, password: String): ResponseLogin?
}