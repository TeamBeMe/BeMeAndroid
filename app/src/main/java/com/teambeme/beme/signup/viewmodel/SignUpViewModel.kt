package com.teambeme.beme.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    val isPersonalChecked = MutableLiveData<Boolean>(false)
    val isServiceChecked = MutableLiveData<Boolean>(false)

    val userEmail = MutableLiveData("")
    val userNickName = MutableLiveData("")
    val userPassWord = MutableLiveData("")
    val userPassWordCheck = MutableLiveData("")

    private val _isEmailValidated = MutableLiveData<Boolean>(false)
    val isEmailValidated: LiveData<Boolean>
        get() = _isEmailValidated
    private val _isNickNameValidated = MutableLiveData(false)
    val isNickNameValidated: LiveData<Boolean>
        get() = _isNickNameValidated
    private val _isPassWordValidated = MutableLiveData(false)
    val isPassWordValidated: LiveData<Boolean>
        get() = _isPassWordValidated
    private val _isPassWordCheckValidated = MutableLiveData(false)
    val isPassWordCheckValidated: LiveData<Boolean>
        get() = _isPassWordCheckValidated

    fun emailValidated() {
        _isEmailValidated.value = true
    }

    fun emailNotValidated() {
        _isEmailValidated.value = false
    }

    fun nickNameValidated() {
        _isNickNameValidated.value = true
    }

    fun nickNameNotValidated() {
        _isNickNameValidated.value = false
    }

    fun passWordValidated() {
        _isPassWordValidated.value = true
    }

    fun passWordNotValidated() {
        _isPassWordValidated.value = false
    }

    fun passWordCheckValidated() {
        _isPassWordCheckValidated.value = true
    }

    fun passWordCheckNotValidated() {
        _isPassWordCheckValidated.value = false
    }

    fun validateAllValues() =
        isEmailValidated.value!! && isNickNameValidated.value!! && isPassWordValidated.value!! && isPassWordCheckValidated.value!!
}