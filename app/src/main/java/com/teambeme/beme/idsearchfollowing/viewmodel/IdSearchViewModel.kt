package com.teambeme.beme.idsearchfollowing.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.idsearchfollowing.model.*
import com.teambeme.beme.idsearchfollowing.repository.IdSearchRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IdSearchViewModel(private val idSearchRepository: IdSearchRepository) : ViewModel() {
    private var copyRecentSearchList: MutableList<ResponseRecentSearchRecord.Data> = mutableListOf()
    var searchingId: String = ""

    private val _recentSearchData = MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
    val recentSearchData: MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>
        get() = _recentSearchData

    private var tempIdSearchList: MutableList<ResponseIdSearchData.Data>? = mutableListOf()

    private val _idSearchData = MutableLiveData<MutableList<ResponseIdSearchData.Data>>()
    val idSearchData: LiveData<MutableList<ResponseIdSearchData.Data>>
        get() = _idSearchData

    private var searchQuery: String = ""
    fun setSearchQuery(query: String) {
        searchQuery = query
    }

    fun deleteQuery(){
        searchQuery = ""
    }

    private val _deletePosition = MutableLiveData<Int>()
    val deletePosition: LiveData<Int>
        get() = _deletePosition

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    private var _isFollowing: String = ""
    val isFollowing: String
        get() = _isFollowing

    fun deleteSearchRecord() {
        tempIdSearchList = mutableListOf(
            ResponseIdSearchData.Data(0, null, "", "")
        )
        _idSearchData.value = tempIdSearchList?.toMutableList()
    }

    fun setInitEmpty(){
        _isEmpty.value=false
    }

    fun requestRecentSearchData() {
        idSearchRepository.getRecentSearchRecord()
            .enqueue(
                object : Callback<ResponseRecentSearchRecord> {
                    override fun onResponse(
                        call: Call<ResponseRecentSearchRecord>,
                        response: Response<ResponseRecentSearchRecord>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Network is success", response.body().toString())
                            copyRecentSearchList = response.body()!!.data!!.toMutableList()
                            _recentSearchData.value = copyRecentSearchList.toMutableList()
                        } else {
                            Log.d("Network Error", response.body()?.data.toString())
                            Log.d("Network Error", response.body()?.status.toString())
                            Log.d("Network Error", response.body()?.success.toString())
                            Log.d("Network Error", response.message())
                        }
                    }

                    override fun onFailure(call: Call<ResponseRecentSearchRecord>, t: Throwable) {
                        Log.d("network_requestOtherMinds", "통신실패")
                    }
                }
            )
    }

    fun setPosition(position: Int) {
        _deletePosition.value = position
    }

    fun deleteRecentSearch() {
        idSearchRepository.deleteRecentSearchRecord(
            copyRecentSearchList[deletePosition.value!!].id
        ).enqueue(object : Callback<ResponseDeleteRecentSearchRecord> {
            override fun onResponse(
                call: Call<ResponseDeleteRecentSearchRecord>,
                response: Response<ResponseDeleteRecentSearchRecord>
            ) {
                if (response.isSuccessful) {
                    Log.d("Network Fail", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseDeleteRecentSearchRecord>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun requestIdSearchgData() {
        idSearchRepository.idSearch(
            searchQuery, "all"
        )
            .enqueue(
                object : Callback<ResponseIdSearchData> {
                    override fun onResponse(
                        call: Call<ResponseIdSearchData>,
                        response: Response<ResponseIdSearchData>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Network is success", response.body().toString())
                            tempIdSearchList = response.body()!!.data?.let { mutableListOf(it) }
                            if(tempIdSearchList?.size==0 ||tempIdSearchList==null)
                                _isEmpty.value=true
                            _idSearchData.value = tempIdSearchList?.toMutableList()
                            if (tempIdSearchList == null) {
                                deleteSearchRecord()
                            }
                        } else {
                            Log.d("Network Error", response.body()?.data.toString())
                            Log.d("Network Error", response.body()?.status.toString())
                            Log.d("Network Error", response.body()?.success.toString())
                            Log.d("Network Error", response.message())
                        }
                    }

                    override fun onFailure(call: Call<ResponseIdSearchData>, t: Throwable) {
                        Log.d("network_requestOtherMinds", "통신실패")
                    }
                }
            )
    }

    fun requestFollowAndFollowing(userId: Int) {
        idSearchRepository.putFollowAndFollowing(
            RequestFollowAndFollowing(userId)
        ).enqueue(
            object : Callback<ResponseFollowAndFollowing> {
                override fun onResponse(
                    call: Call<ResponseFollowAndFollowing>,
                    response: Response<ResponseFollowAndFollowing>
                ) {
                    Log.d("network_requestSearch", "통신성")
                    if (response.isSuccessful) {
                        _isFollowing = response.body()!!.message
                    }
                }

                override fun onFailure(call: Call<ResponseFollowAndFollowing>, t: Throwable) {
                    Log.d("network_requestSearch", "통신실패")
                }
            }
        )
    }
}