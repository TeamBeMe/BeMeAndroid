package com.teambeme.beme.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teambeme.beme.R
import com.teambeme.beme.data.local.singleton.BeMeRepository
import com.teambeme.beme.data.repository.LoginRepository
import com.teambeme.beme.login.model.ResponseLogin
import com.teambeme.beme.onboarding.model.OnBoardingData
import com.teambeme.beme.util.ErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _responseValue = MutableLiveData<ResponseLogin?>()
    val responseValue: LiveData<ResponseLogin?>
        get() = _responseValue

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getOnBoardingDatas() = mutableListOf(
        OnBoardingData(
            imgOnBoarding = R.drawable.ic_onboarding_01,
            txtOnBoardingTitle = "생각해 보세요",
            txtOnBoardingContents = "나는 어떤 사람인가요\n" +
                    "내게 가장 소중한 것은 무엇인가요?\n" +
                    "내 삶의 목적은 무엇인가요?\n"
        ),
        OnBoardingData(
            imgOnBoarding = R.drawable.ic_onboarding_02,
            txtOnBoardingTitle = "진짜 나를 찾아보세요",
            txtOnBoardingContents = "BeMe가 여러분께\n" +
                    "매일 10시에 질문을 보내드릴게요.\n" +
                    "질문에 답을 하면서 진정한 나를 찾을 수 있도록 도와드릴게요.\n"
        ),
        OnBoardingData(
            imgOnBoarding = R.drawable.ic_onboarding_03,
            txtOnBoardingTitle = "사람들과 소통해보세요",
            txtOnBoardingContents = "내가 받은 질문에 다른 사람들은 \n" +
                    "어떻게 생각하는지 볼 수 있어요\n" +
                    "내가 원한다면\n" +
                    "글을 공개하여 사람들과 소통할 수 있어요"
        ),
        OnBoardingData(
            imgOnBoarding = R.drawable.ic_onboarding_04,
            txtOnBoardingTitle = "생각해 보세요",
            txtOnBoardingContents = "1년 후 같은 질문에 답을 해보세요\n" +
                    "과거의 나는 어떻게 생각했는지, 내 생각이\n" +
                    "어떻게 변했는지 알아보세요"
        )
    )

    fun requestLogin() {
        loginRepository.login(
            BeMeRepository.userId,
            BeMeRepository.userPassword
        ).enqueue(object :
            Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    _responseValue.value = response.body()
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