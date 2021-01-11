package com.teambeme.beme.notification.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.notification.model.ResponseNoticeData
import com.teambeme.beme.notification.repository.NoticeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeViewModel(private val noticeRepository: NoticeRepository) : ViewModel() {
    private var copyNoticeDataList: MutableList<ResponseNoticeData.Data.Activity> = mutableListOf()

    private val _noticeList = MutableLiveData<MutableList<ResponseNoticeData.Data.Activity>>()
    val noticeDataList: MutableLiveData<MutableList<ResponseNoticeData.Data.Activity>>
        get() = _noticeList

    var page: Int = 1

    private val _isMax = MutableLiveData<Boolean>()
    val isMax: LiveData<Boolean>
        get() = _isMax

    fun requestNoticeItem() {
        Log.d("Network ViewModel", "start")
        noticeRepository.notice(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page
        ).enqueue(object :
            Callback<ResponseNoticeData> {
            override fun onResponse(
                call: Call<ResponseNoticeData>,
                responseData: Response<ResponseNoticeData>
            ) {
                if (responseData.isSuccessful) {
                    Log.d("Network is success", responseData.body().toString())
                    copyNoticeDataList = responseData.body()!!.data?.activities?.toMutableList()
                    _noticeList.value = copyNoticeDataList.toMutableList()
                    Log.d("Network is success", copyNoticeDataList.toString())
                    when (page == responseData.body()!!.data.pageLen) {
                        true -> _isMax.value = true
                        else -> page++
                    }
                }
                else {
                    Log.d("Network Error", responseData.body()?.data.toString())
                    Log.d("Network Error", responseData.body()?.status.toString())
                    Log.d("Network Error", responseData.body()?.success.toString())
                    Log.d("Network Error", responseData.message())
                }

            }

            override fun onFailure(call: Call<ResponseNoticeData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun requestAddNoticeItem() {
        noticeRepository.notice(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page
        ).enqueue(object :
            Callback<ResponseNoticeData> {
            override fun onResponse(
                call: Call<ResponseNoticeData>,
                responseData: Response<ResponseNoticeData>
            ) {
                if (responseData.isSuccessful) {
                    copyNoticeDataList.addAll(responseData.body()!!.data?.activities.toMutableList())
                    _noticeList.value = copyNoticeDataList.toMutableList()
                    when (page == responseData.body()!!.data?.pageLen) {
                        true -> _isMax.value = true
                        else -> page++
                    }
                }
            }

            override fun onFailure(call: Call<ResponseNoticeData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }
}