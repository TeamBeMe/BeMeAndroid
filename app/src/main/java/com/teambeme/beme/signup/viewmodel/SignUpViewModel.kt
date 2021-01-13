package com.teambeme.beme.signup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.data.remote.datasource.SignUpDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import com.teambeme.beme.signup.repository.SignUpRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel : ViewModel() {
    private val signUpRepository =
        SignUpRepositoryImpl(SignUpDataSourceImpl(RetrofitObjects.getSignUpService()))
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
    private val _isNickNameDoubleChecked = MutableLiveData(false)
    val isNickNameDoubleChecked: LiveData<Boolean>
        get() = _isNickNameDoubleChecked
    private val _isPassWordValidated = MutableLiveData(false)
    val isPassWordValidated: LiveData<Boolean>
        get() = _isPassWordValidated
    private val _isPassWordCheckValidated = MutableLiveData(false)
    val isPassWordCheckValidated: LiveData<Boolean>
        get() = _isPassWordCheckValidated
    private val _nickNameDoubleCheck = MutableLiveData<ResponseNickDoubleCheck>()
    val nickDoubleCheck: LiveData<ResponseNickDoubleCheck>
        get() = _nickNameDoubleCheck

    private val _signUpUserInfo = MutableLiveData<ResponseSignUp?>()
    val signUpUserInfo: LiveData<ResponseSignUp?>
        get() = _signUpUserInfo

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

    fun signUp() = viewModelScope.launch {
        _signUpUserInfo.value = signUpRepository.signUp(
            userEmail.value!!,
            userNickName.value!!,
            userPassWord.value!!,
            null
        )
    }

    fun signUpWithoutImage() = viewModelScope.launch {
        Log.d("SignUp", userEmail.value!!)
        Log.d("SignUp", userNickName.value!!)
        Log.d("SignUp", userPassWord.value!!)
        _signUpUserInfo.value = signUpRepository.signUp(
            userEmail.value!!,
            userNickName.value!!,
            userPassWord.value!!,
            null
        )
        Log.d("SignUp", _signUpUserInfo.value.toString())
    }

    fun nickNameDoubleCheck() = viewModelScope.launch {
        try {
            _nickNameDoubleCheck.value = signUpRepository.nickNameDoubleCheck(userNickName.value!!)
        } catch (e: HttpException) {
            Log.d("", e.message())
        }
    }
}