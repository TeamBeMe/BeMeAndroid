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
import okhttp3.MultipartBody
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
    // TermFragment
    val isPersonalChecked = MutableLiveData(false)
    val isServiceChecked = MutableLiveData(false)

    // PersonalInfoFragment
    private lateinit var userInfo: User
    private val validatedId = hashSetOf<String>()
    fun isDoubleCheckedId(nickName: String) = validatedId.contains(nickName)
    fun registerId(nickName: String) {
        validatedId.add(nickName)
    }

    val userEmail = MutableLiveData("")
    val userNickName = MutableLiveData("")
    val userPassWord = MutableLiveData("")
    val userPassWordCheck = MutableLiveData("")

    // Email
    val isEmailValid = Transformations.map(userEmail) { validEmail(it) }

    // Nickname
    private val isNicknameLengthValid =
        Transformations.map(userNickName) { nickNameLengthValidation(it) }
    private val isNicknameRegexValid =
        Transformations.map(userNickName) { regexValid(it) }
    val isNicknameValid = MediatorLiveData<Boolean>().apply {
        addSourceList(isNicknameLengthValid, isNicknameRegexValid) { nicknameValid() }
    }
    private val _nickNameDoubleCheck = MutableLiveData<ResponseNickDoubleCheck>()
    val nickDoubleCheck: LiveData<ResponseNickDoubleCheck>
        get() = _nickNameDoubleCheck
    private val _isNickNameDoubleChecked = MutableLiveData(false)
    val isNickNameDoubleChecked: LiveData<Boolean>
        get() = _isNickNameDoubleChecked

    // Password
    private val isPasswordRegexValid = Transformations.map(userPassWord) { regexValid(it) }
    private val isPasswordLengthValid =
        Transformations.map(userPassWord) { passwordLengthValid(it) }
    val isPasswordValid = MediatorLiveData<Boolean>().apply {
        addSourceList(isPasswordRegexValid, isPasswordLengthValid) { passwordValid() }
    }
    private val _isPasswordDoubleChecked = MediatorLiveData<Boolean>().apply {
        addSourceList(userPassWord, userPassWordCheck) { passwordDoubleCheckValid() }
    }
    val isPasswordDoubleChecked: LiveData<Boolean>
        get() = _isPasswordDoubleChecked


    val isDoneButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSourceList(
            isEmailValid, isPasswordDoubleChecked, isPasswordRegexValid, isPasswordLengthValid,
            isNicknameLengthValid, isNicknameRegexValid, isNickNameDoubleChecked
        ) { validUserInfo() }
    }
    private val _profileImageUri = MutableLiveData<Uri>()
    val profileImageUri: LiveData<Uri>
        get() = _profileImageUri
    private val _profilePart = MutableLiveData<MultipartBody.Part>()
    val profilePart: LiveData<MultipartBody.Part>
        get() = _profilePart

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _signUpUserInfo = MutableLiveData<ResponseSignUp?>()
    val signUpUserInfo: LiveData<ResponseSignUp?>
        get() = _signUpUserInfo

    fun nickDoubleCheckValidated() {
        _isNickNameDoubleChecked.value = true
    }

    fun nickDoubleCheckInvalidated() {
        _isNickNameDoubleChecked.value = false
    }

    fun signUp() = viewModelScope.launch {
        if (profilePart.value != null) {
            try {
                _signUpUserInfo.value =
                    signUpRepository.signUp(userInfo.toRequestBody(), profilePart.value!!)
            } catch (e: HttpException) {
                Log.d("SignUp", e.code().toString())
                Log.d("SignUp", e.message())
                Log.d("SignUp", e.stackTraceToString())
            }
        } else {
            try {
                _signUpUserInfo.value = signUpRepository.signUp(userInfo.toRequestBody(), null)
            } catch (e: HttpException) {
                Log.d("SignUp", e.code().toString())
                Log.d("SignUp", e.message())
                Log.d("SignUp", e.stackTraceToString())
            }
        }
    }

    fun signUpWithoutImage() = viewModelScope.launch {
        _signUpUserInfo.value = signUpRepository.signUp(userInfo.toRequestBody(), null)
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

    // NEW Functions

    private fun validEmail(email: String): Boolean {
        return REGEX_EMAIL.matches(email)
    }

    private fun passwordDoubleCheckValid(): Boolean {
        if (userPassWord.value.isNullOrEmpty() || userPassWordCheck.value.isNullOrEmpty())
            return false
        return userPassWord.value == userPassWordCheck.value
    }

    private fun nicknameValid() = isNicknameLengthValid.value ?: false &&
            isNicknameLengthValid.value ?: false

    private fun passwordValid() = isPasswordRegexValid.value ?: false
            && isPasswordLengthValid.value ?: false

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
                && isPasswordLengthValid.value!! && isNickNameDoubleChecked.value!!

    companion object {
        private val REGEX_EMAIL = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    }
}