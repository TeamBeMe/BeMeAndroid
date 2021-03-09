package com.teambeme.beme.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.login.repository.LoginRepository
import com.teambeme.beme.util.ErrorBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    var nickNameText = MutableLiveData<String>()
    var passwordText = MutableLiveData<String>()

    private val _responseValue = MutableLiveData<ResponseLogin?>()
    val responseValue: LiveData<ResponseLogin?>
        get() = _responseValue

    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _showPassword = MutableLiveData(false)
    val showPassword: LiveData<Boolean>
        get() = _showPassword

    fun setShowPassword() {
        _showPassword.value = showPassword.value != true
    }

    fun requestLogin() {
        loginRepository.login(nickNameText.value ?: "", passwordText.value ?: "").enqueue(object :
            Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    _responseValue.value = response.body()
                    BeMeAuthPreference.userId = nickNameText.value!!
                    BeMeAuthPreference.userPassword = passwordText.value!!
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorBody>() {}.type
                    val errorResponse: ErrorBody? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                    _errorMessage.value = "아이디 또는 비밀번호를 다시 확인해주세요"
                }
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