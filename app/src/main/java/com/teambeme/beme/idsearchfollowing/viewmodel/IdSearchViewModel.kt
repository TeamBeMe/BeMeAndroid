package com.teambeme.beme.idsearchfollowing.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.idsearchfollowing.model.ResponseDeleteRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
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

    private val _idSearchData = MutableLiveData<MutableList<ResponseIdSearchData.Data?>>()
    val idSearchData: LiveData<MutableList<ResponseIdSearchData.Data?>>
        get() = _idSearchData

    private val _deletePosition = MutableLiveData<Int>()
    val deletePosition: LiveData<Int>
        get() = _deletePosition

    fun requestRecentSearchData() {
        idSearchRepository.getRecentSearchRecord("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI")
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
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
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
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            searchingId, "all"
        )
            .enqueue(
                object : Callback<ResponseIdSearchData> {
                    override fun onResponse(
                        call: Call<ResponseIdSearchData>,
                        response: Response<ResponseIdSearchData>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Network is success", response.body().toString())
                            _idSearchData.value = mutableListOf(response.body()!!.data)
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

}