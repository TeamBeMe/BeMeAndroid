package com.teambeme.beme.data.repository

import com.teambeme.beme.login.model.ResponseLogin
import retrofit2.Call

interface LoginRepository {
    fun login(nickname: String, password: String): Call<ResponseLogin>
}