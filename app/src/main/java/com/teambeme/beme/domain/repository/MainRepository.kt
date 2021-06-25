package com.teambeme.beme.domain.repository

import com.teambeme.beme.main.model.ResponseFbTokenRegister
import retrofit2.Call

interface MainRepository {
    fun fbTokenRegister(fb_token: String): Call<ResponseFbTokenRegister>
}
