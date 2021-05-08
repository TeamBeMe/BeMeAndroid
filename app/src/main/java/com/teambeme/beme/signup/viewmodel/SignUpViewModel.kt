package com.teambeme.beme.signup.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.teambeme.beme.data.repository.SignUpRepository
import com.teambeme.beme.signup.domain.entity.User
import com.teambeme.beme.signup.model.ResponseNickDoubleCheck
import com.teambeme.beme.signup.model.ResponseSignUp
import com.teambeme.beme.util.addSourceList
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
    // TermFragment
    val isPersonalChecked = MutableLiveData<Boolean>(false)
    val isServiceChecked = MutableLiveData<Boolean>(false)

    // PersonalInfoFragment
    private lateinit var userInfo: User

    val userEmail = MutableLiveData("")
    val userNickName = MutableLiveData("")
    val userPassWord = MutableLiveData("")
    val userPassWordCheck = MutableLiveData("")
    private val isEmailValid = Transformations.map(userEmail) { validEmail(it) }
    private val _isPasswordDoubleChecked = MediatorLiveData<Boolean>().apply {
        addSourceList(userPassWord, userPassWordCheck) { passwordValid() }
    }
    private val isPasswordDoubleChecked: LiveData<Boolean>
        get() = _isPasswordDoubleChecked
    private val isPasswordRegexValid = Transformations.map(userPassWord) { regexValid(it) }
    private val isPasswordLengthValid =
        Transformations.map(userPassWord) { passwordLengthValid(it) }
    private val isNicknameLengthValid =
        Transformations.map(userNickName) { nickNameLengthValidation(it) }
    private val isNicknameRegexValid =
        Transformations.map(userNickName) { regexValid(it) }

    val isDoneButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSourceList(
            isEmailValid, isPasswordDoubleChecked, isPasswordRegexValid, isPasswordLengthValid,
            isNicknameLengthValid, isNicknameRegexValid
        ) { validUserInfo() }
    }

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

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

    fun nickNameDoubleCheck() {
        viewModelScope.launch {
            runCatching { signUpRepository.nickNameDoubleCheck(requireNotNull(userNickName.value)) }
                .onSuccess { _nickNameDoubleCheck.value = it }
                .onFailure { it.printStackTrace() }
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

    private fun validEmail(email: String): Boolean {
        return REGEX_EMAIL.matches(email)
    }

    private fun passwordValid(): Boolean {
        if (userPassWord.value.isNullOrEmpty() || userPassWordCheck.value.isNullOrEmpty())
            return false
        return userPassWord.value == userPassWordCheck.value
    }

    private fun regexValid(letter: String) = letter
        .filter { it in 'A'..'Z' || it in 'a'..'z' || it in '0'..'9' }
        .length == letter.length

    private fun passwordLengthValid(password: String) = password.length in 8..20

    private fun nickNameLengthValidation(nickName: String): Boolean = nickName.length in 5..20

    fun setUserInfo() {
        userInfo = User(
            email = requireNotNull(userEmail.value),
            nickName = requireNotNull(userNickName.value),
            password = requireNotNull(userPassWord.value)
        )
    }

    private fun validUserInfo() =
        isEmailValid.value!! && isPasswordDoubleChecked.value!! && isPasswordRegexValid.value!!
                && isPasswordLengthValid.value!! && isNicknameLengthValid.value!! && isNicknameRegexValid.value!!

    companion object {
        private val REGEX_EMAIL = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    }
}