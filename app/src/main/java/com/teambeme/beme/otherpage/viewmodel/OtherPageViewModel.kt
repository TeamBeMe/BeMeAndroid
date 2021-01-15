package com.teambeme.beme.otherpage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherData
import com.teambeme.beme.otherpage.model.ResponseOtherData.Data.Answer
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import com.teambeme.beme.otherpage.repository.OtherPageRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherPageViewModel(private val otherRepository: OtherPageRepository) : ViewModel() {
    private var copyOtherAnswerList: MutableList<Answer> = mutableListOf()

    private val _otherAnswerList = MutableLiveData<MutableList<Answer>>()
    val otherAnswerList: LiveData<MutableList<Answer>>
        get() = _otherAnswerList

    private val _otherUserInfo = MutableLiveData<ResponseOtherInfo.Data>()
    val otherUserInfo: LiveData<ResponseOtherInfo.Data>
        get() = _otherUserInfo

    var page: Int = 1
    private val _isMax = MutableLiveData<Boolean>()
    val isMax: LiveData<Boolean>
        get() = _isMax

    private val _scrapPosition = MutableLiveData<Int>()
    val scrapPosition: LiveData<Int>
        get() = _scrapPosition

    fun setPosition(position: Int) {
        _scrapPosition.value = position
    }

    private val _isOtherEmpty = MutableLiveData<Boolean>()
    val isOtherEmpty: LiveData<Boolean>
        get() = _isOtherEmpty

    fun putScrap() {
        otherRepository.putScrap(
            copyOtherAnswerList[scrapPosition.value!!].id
        ).enqueue(object : Callback<ResponseScrap> {
            override fun onResponse(
                call: Call<ResponseScrap>,
                response: Response<ResponseScrap>
            ) {
                if (response.isSuccessful) {
                }
            }

            override fun onFailure(call: Call<ResponseScrap>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    private val _isFollow = MutableLiveData<Boolean>()
    val isFollow: LiveData<Boolean>
        get() = _isFollow

    fun putFollow() {
        _isFollow.value = !_isFollow.value!!
        otherRepository.putFollow(
            otherUserInfo.value!!.id
        ).enqueue(object : Callback<ResponseFollow> {
            override fun onResponse(
                call: Call<ResponseFollow>,
                response: Response<ResponseFollow>
            ) {
                if (response.isSuccessful) {
                }
            }

            override fun onFailure(call: Call<ResponseFollow>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun requestUser(userId: Int) {
        page = 1
        otherRepository.getOtherInfo(
            userId
        )
            .enqueue(object :
                Callback<ResponseOtherInfo> {
                override fun onResponse(
                    call: Call<ResponseOtherInfo>,
                    response: Response<ResponseOtherInfo>
                ) {
                    if (response.isSuccessful) {
                        _otherUserInfo.value = response.body()!!.data
                        _isFollow.value = response.body()!!.data.isFollowed
                    }
                }

                override fun onFailure(call: Call<ResponseOtherInfo>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun requestAddItem(userId: Int) {
        otherRepository.getProfileAnswer(
            userId,
            page
        ).enqueue(object :
            Callback<ResponseOtherData> {
            override fun onResponse(
                call: Call<ResponseOtherData>,
                response: Response<ResponseOtherData>
            ) {
                if (response.isSuccessful) {
                    copyOtherAnswerList.addAll(response.body()!!.data?.answers?.toMutableList())
                    _otherAnswerList.value = copyOtherAnswerList.toMutableList()
                    when (page < response.body()!!.data.pageLen) {
                        true -> {
                            _isMax.value = true
                        }
                        else -> {
                            page++
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseOtherData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun requestItem(userId: Int) {
        otherRepository.getProfileAnswer(
            userId,
            page
        ).enqueue(object :
            Callback<ResponseOtherData> {
            override fun onResponse(
                call: Call<ResponseOtherData>,
                response: Response<ResponseOtherData>
            ) {
                if (response.isSuccessful) {
                    copyOtherAnswerList = response.body()!!.data?.answers?.toMutableList()
                    _isOtherEmpty.value = copyOtherAnswerList.size == 0
                    _otherAnswerList.value = copyOtherAnswerList.toMutableList()
                    when (page < response.body()!!.data.pageLen) {
                        true -> {
                            _isMax.value = true
                        }
                        else -> {
                            page++
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseOtherData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }
}