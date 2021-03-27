package com.teambeme.beme.notification.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.notification.model.ResponseNoticeData
import com.teambeme.beme.data.repository.NoticeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeViewModel(private val noticeRepository: NoticeRepository) : ViewModel() {
    private var copyNoticeDataList: MutableList<ResponseNoticeData.Data.Activity> = mutableListOf()

    private val _noticeDataList = MutableLiveData<MutableList<ResponseNoticeData.Data.Activity>>()
    val noticeDataList: MutableLiveData<MutableList<ResponseNoticeData.Data.Activity>>
        get() = _noticeDataList

    private var _page: Int = 1
    val page: Int
        get() = _page

    private val _isMax = MutableLiveData<Boolean>()
    val isMax: LiveData<Boolean>
        get() = _isMax

    private var _isMorePage = MutableLiveData(false)
    val isMorePage: LiveData<Boolean>
        get() = _isMorePage

    fun requestNoticeItem() {
        Log.d("Network ViewModel", "start")
        noticeRepository.notice(
            _page
        ).enqueue(object :
            Callback<ResponseNoticeData> {
            override fun onResponse(
                call: Call<ResponseNoticeData>,
                responseData: Response<ResponseNoticeData>
            ) {
                if (responseData.isSuccessful) {
                    Log.d("Network is success", responseData.body().toString())
                    copyNoticeDataList = responseData.body()!!.data?.activities?.toMutableList()
                    _noticeDataList.value = copyNoticeDataList.toMutableList()
                    Log.d("Network is success", copyNoticeDataList.toString())
                    if (responseData.body()!!.data != null) {
                        if (responseData.body()!!.data?.pageLen > _page) {
                            _page++
                            _isMorePage.value = true
                        } else {
                            _isMorePage.value = false
                        }
                    } else {
                        _isMorePage.value = false
                    }
                    Log.d("notice_request_copy", "$copyNoticeDataList")
                    val d = Log.d("notice_request_data", "${noticeDataList.value}")
                } else {
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
            _page
        ).enqueue(object :
            Callback<ResponseNoticeData> {
            override fun onResponse(
                call: Call<ResponseNoticeData>,
                responseData: Response<ResponseNoticeData>
            ) {
                if (responseData.isSuccessful) {
                    copyNoticeDataList.addAll(responseData.body()!!.data?.activities.toMutableList())
                    _noticeDataList.value = copyNoticeDataList.toMutableList()
                    if (responseData.body()!!.data != null) {
                        if (responseData.body()!!.data?.pageLen > _page) {
                            _page++
                            _isMorePage.value = true
                        } else {
                            _isMorePage.value = false
                        }
                    } else {
                        _isMorePage.value = false
                    }
                    Log.d("notice_plus_copy", "$copyNoticeDataList")
                    val d = Log.d("notice_plus_data", "${noticeDataList.value}")
                }
            }

            override fun onFailure(call: Call<ResponseNoticeData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }
}