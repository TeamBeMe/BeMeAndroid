package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.main.model.ResponseFbTokenRegister
import retrofit2.Call

interface FbTokenRegisterDataSource {
    fun fbTokenRegister(fb_token: String): Call<ResponseFbTokenRegister>
}