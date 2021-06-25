package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.data.remote.api.FbTokenRegisterService
import com.teambeme.beme.main.model.RequestFbTokenRegister
import com.teambeme.beme.main.model.ResponseFbTokenRegister
import javax.inject.Inject
import retrofit2.Call

class FbTokenRegisterDataSourceImpl @Inject constructor(
    private val service: FbTokenRegisterService
) : FbTokenRegisterDataSource {
    override fun fbTokenRegister(fb_token: String): Call<ResponseFbTokenRegister> {
        return service.fbTokenRegister(RequestFbTokenRegister(fb_token))
    }
}
