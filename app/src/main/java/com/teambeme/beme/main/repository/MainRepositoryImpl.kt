package com.teambeme.beme.main.repository

import com.teambeme.beme.data.remote.datasource.FbTokenRegisterDataSource
import com.teambeme.beme.main.model.ResponseFbTokenRegister
import retrofit2.Call

class MainRepositoryImpl(private val fbTokenRegisterDataSource: FbTokenRegisterDataSource) :
    MainRepository {
    override fun fbTokenRegister(
        token: String,
        fb_token: String
    ): Call<ResponseFbTokenRegister> =
        fbTokenRegisterDataSource.fbTokenRegister(
            token, fb_token
        )
}
