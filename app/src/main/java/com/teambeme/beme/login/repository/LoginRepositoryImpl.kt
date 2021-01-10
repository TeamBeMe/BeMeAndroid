package com.teambeme.beme.login.repository

import android.util.Log
import com.teambeme.beme.data.remote.datasource.LoginDataSource
import com.teambeme.beme.login.model.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {

    override fun login(nickname: String, password: String): ResponseLogin? {
        var responseLogin: ResponseLogin? = null
        loginDataSource.login(nickname, password).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                Log.d("Network Repo 1", response.body()!!.toString())
                if (response.isSuccessful)
                    responseLogin = response.body()!!
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
                for (element in t.stackTrace) {
                    Log.d("Network element", element.toString())
                    Log.d("Network className", element.className)
                    Log.d("Network methodName", element.methodName)
                    Log.d("Network fileName", element.fileName)
                    Log.d("Network lineNumber", element.lineNumber.toString())
                }
            }
        })
        Log.d("Network Repo 2", responseLogin.toString())
        return responseLogin
    }
}