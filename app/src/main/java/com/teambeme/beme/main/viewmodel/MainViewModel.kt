package com.teambeme.beme.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import com.teambeme.beme.data.repository.MainRepository
import com.teambeme.beme.main.model.ResponseFbTokenRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getFireBaseToken() {
        mainRepository.fbTokenRegister(
            fb_token = BeMeAuthPreference.fireBaseToken
        ).enqueue(object : Callback<ResponseFbTokenRegister> {
            override fun onResponse(
                call: Call<ResponseFbTokenRegister>,
                response: Response<ResponseFbTokenRegister>
            ) {
                if (response.isSuccessful) {
                    Log.d("FireBase", "등록이 완료되었습니다.")
                } else {
                    Log.d("FireBase", "등록 과정에서 오류가 발생되었습니다.")
                }
            }

            override fun onFailure(call: Call<ResponseFbTokenRegister>, t: Throwable) {
            }
        })
    }
}