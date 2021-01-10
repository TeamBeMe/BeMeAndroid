package com.teambeme.beme.data.remote.api

import com.teambeme.beme.login.model.RequestLogin
import com.teambeme.beme.login.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Accept: application/json",
        "Content-type: application/json")
    @POST("users/signin")
    fun login(
        @Body body: RequestLogin
    ): Call<ResponseLogin>
}