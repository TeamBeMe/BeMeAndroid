package com.teambeme.beme.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.login.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    var nickNameText = MutableLiveData<String>()
    var passwordText = MutableLiveData<String>()

    private val _responseValue = MutableLiveData<ResponseLogin>()
    val responseValue: LiveData<ResponseLogin>
        get() = _responseValue

    fun requestLogin() {
        val responseValue = loginRepository.login(nickNameText.value ?: "", passwordText.value ?: "")
        Log.d("Network ViewModel", responseValue.toString())
        _responseValue.value = responseValue
    }
}