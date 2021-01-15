package com.teambeme.beme.login.repository

import com.teambeme.beme.data.remote.datasource.LoginDataSource

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {
    override fun login(nickname: String, password: String) =
        loginDataSource.login(nickname, password)
}