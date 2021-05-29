package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.FbTokenRegisterDataSource
import com.teambeme.beme.main.model.ResponseFbTokenRegister
import javax.inject.Inject
import retrofit2.Call

class MainRepositoryImpl @Inject constructor(
    private val fbTokenRegisterDataSource: FbTokenRegisterDataSource
) : MainRepository {
    override fun fbTokenRegister(fb_token: String): Call<ResponseFbTokenRegister> =
        fbTokenRegisterDataSource.fbTokenRegister(fb_token)
}
