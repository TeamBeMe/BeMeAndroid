package com.teambeme.beme.data.remote.api

import com.teambeme.beme.main.model.RequestFbTokenRegister
import com.teambeme.beme.main.model.ResponseFbTokenRegister
import retrofit2.Call
import retrofit2.http.*

interface FbTokenRegisterService {
    @Headers("Content-type: application/json")
    @POST("users/fb-token")
    fun fbTokenRegister(
        @Body body: RequestFbTokenRegister
    ): Call<ResponseFbTokenRegister>
}
