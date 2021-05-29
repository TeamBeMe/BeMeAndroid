package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.login.model.ResponseLogin
import retrofit2.Call

interface LoginDataSource {
    fun login(nickname: String, password: String): Call<ResponseLogin>
}
