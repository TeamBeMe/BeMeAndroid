package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.LoginDataSource
import com.teambeme.beme.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override fun login(nickname: String, password: String) =
        loginDataSource.login(nickname, password)
}
