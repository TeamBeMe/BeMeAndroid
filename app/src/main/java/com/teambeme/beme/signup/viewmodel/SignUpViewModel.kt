package com.teambeme.beme.signup.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.data.repository.SignUpRepository
import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
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
    private val _profileImageUri = MutableLiveData<Uri>()
    val profileImageUri: LiveData<Uri>
        get() = _profileImageUri
    private val _profilePart = MutableLiveData<MultipartBody.Part>()
    val profilePart: LiveData<MultipartBody.Part>
        get() = _profilePart

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

    fun nickDoubleCheckValidated() {
        _isNickNameDoubleChecked.value = true
    }

    fun validateAllValues() =
        isEmailValidated.value!! && isNickNameValidated.value!! && isPassWordValidated.value!! && isPassWordCheckValidated.value!! && isNickNameDoubleChecked.value!!

    fun signUp() = viewModelScope.launch {
        if (profilePart.value != null) {
            try {
                _signUpUserInfo.value = signUpRepository.signUp(getPartMap(), profilePart.value!!)
            } catch (e: HttpException) {
                Log.d("SignUp", e.code().toString())
                Log.d("SignUp", e.message())
                Log.d("SignUp", e.stackTraceToString())
            }
        } else {
            try {
                _signUpUserInfo.value = signUpRepository.signUp(getPartMap(), null)
            } catch (e: HttpException) {
                Log.d("SignUp", e.code().toString())
                Log.d("SignUp", e.message())
                Log.d("SignUp", e.stackTraceToString())
            }
        }
    }

    fun signUpWithoutImage() = viewModelScope.launch {
        _signUpUserInfo.value = signUpRepository.signUp(getPartMap(), null)
        Log.d("SignUp", _signUpUserInfo.value.toString())
    }

    fun nickNameDoubleCheck() = viewModelScope.launch {
        try {
            _nickNameDoubleCheck.value = signUpRepository.nickNameDoubleCheck(userNickName.value!!)
        } catch (e: HttpException) {
            Log.d("", e.message())
        }
    }

    fun setProfilePart(part: MultipartBody.Part) {
        _profilePart.value = part
    }

    fun setProfileUri(uri: Uri) {
        _profileImageUri.value = uri
    }

    private fun getPartMap(): HashMap<String, RequestBody> {
        val email = userEmail.value!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val nickName = userNickName.value!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val passWord = userPassWord.value!!.toRequestBody("text/plain".toMediaTypeOrNull())
        return hashMapOf(
            "email" to email,
            "nickname" to nickName,
            "password" to passWord
        )
    }
}