package com.teambeme.beme.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.login.repository.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    var nickNameText = MutableLiveData<String>()
    var passwordText = MutableLiveData<String>()

    private val _responseValue = MutableLiveData<ResponseLogin?>()
    val responseValue: LiveData<ResponseLogin?>
        get() = _responseValue

    fun requestLogin() {
        loginRepository.login(nickNameText.value ?: "", passwordText.value ?: "").enqueue(object :
            Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                Log.d("Login", response.body().toString())
                Log.d("Login", response.message())
                Log.d("Login", response.headers().toString())
                _responseValue.value = response.body()
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
    }
}