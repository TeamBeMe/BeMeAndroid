package com.teambeme.beme.main.repository

import com.teambeme.beme.main.model.ResponseFbTokenRegister
import retrofit2.Call

interface MainRepository{
    fun fbTokenRegister(token: String, fb_token: String): Call<ResponseFbTokenRegister>
}
