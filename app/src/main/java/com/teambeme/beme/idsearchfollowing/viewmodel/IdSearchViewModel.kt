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

    private val _searchedData = MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
    val searchedData: MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>
        get() = _searchedData

    fun requestSearchedData() {
        idSearchRepository.getRecentSearchRecord("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ")
            .enqueue(
                object : Callback<ResponseRecentSearchRecord> {
                    override fun onResponse(
                        call: Call<ResponseRecentSearchRecord>,
                        response: Response<ResponseRecentSearchRecord>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Network is success", response.body().toString())
                            copyRecentSearchList = response.body()!!.data!!.toMutableList()
                            _searchedData.value = copyRecentSearchList.toMutableList()

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

    private val _deletePosition = MutableLiveData<Int>()
    val deletePosition: LiveData<Int>
        get() = _deletePosition

    fun setPosition(position: Int) {
        _deletePosition.value = position
    }

    fun deleteRecentSearch() {
        idSearchRepository.deleteRecentSearchRecord(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
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

    private val _searchingData = MutableLiveData<ResponseIdSearchData.Data>()
    val searchingData: MutableLiveData<ResponseIdSearchData.Data>
        get() = _searchingData

    var serchingId : String = ""

    fun requestSearchingData() {
        idSearchRepository.idSearch(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            serchingId,"")
            .enqueue(
                object : Callback<ResponseIdSearchData> {
                    override fun onResponse(
                        call: Call<ResponseIdSearchData>,
                        response: Response<ResponseIdSearchData>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Network is success", response.body().toString())
                            _searchingData.value = response.body()!!.data
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


//    val query = MutableLiveData<String>()
//    val range = MutableLiveData<String>()
//
//    private val _idSearchData = MutableLiveData<ResponseIdSearchData.Data>()
//    val idSearchData: MutableLiveData<ResponseIdSearchData.Data>
//        get() = _idSearchData
//
//    fun requestIdSearchResult() {
//        idSearchRepository.idSearch(
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiaWF0IjoxNjEwMjkwNzgyLCJleHAiOjE2NDE4MjY3ODIsImlzcyI6ImJlbWUifQ.PflJxm_WRMtgjFYtw68aFNNkkEZWNSuT_2kpgfWCNbY",
//            query.value?:"", range.value?:""
//        ).enqueue(object : Callback<ResponseIdSearchData> {
//            override fun onResponse(
//                call: Call<ResponseIdSearchData>,
//                responseData: Response<ResponseIdSearchData>
//            ) {
//                if (responseData.isSuccessful)
//                    _idSearchData.value = responseData.body()!!.data
//            }
//
//            override fun onFailure(call: Call<ResponseIdSearchData>, t: Throwable) {
//                Log.d("Network Fail", t.message.toString())
//                for (element in t.stackTrace) {
//                    Log.d("Network element", element.toString())
//                    Log.d("Network className", element.className)
//                    Log.d("Network methodName", element.methodName)
//                    Log.d("Network fileName", element.fileName)
//                    Log.d("Network lineNumber", element.lineNumber.toString())
//                }
//            }
//        })
//    }
//
//    private val _getRecentSearchRecord =
//        MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
//    val getRecentSearchRecord: LiveData<MutableList<ResponseRecentSearchRecord.Data>>
//        get() = _getRecentSearchRecord
//
//    fun requestGetRecentSearchRecord() {
//        idSearchRepository.getRecentSearchRecord(
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ"
//        )
//            .enqueue(object : Callback<ResponseRecentSearchRecord> {
//                override fun onResponse(
//                    call: Call<ResponseRecentSearchRecord>,
//                    response: Response<ResponseRecentSearchRecord>
//                ) {
//                    if (response.isSuccessful)
//                        _getRecentSearchRecord.value = response.body()!!.data?.toMutableList()
//                }
//
//                override fun onFailure(call: Call<ResponseRecentSearchRecord>, t: Throwable) {
//                    Log.d("Network Fail", t.message.toString())
//                    for (element in t.stackTrace) {
//                        Log.d("Network element", element.toString())
//                        Log.d("Network className", element.className)
//                        Log.d("Network methodName", element.methodName)
//                        Log.d("Network fileName", element.fileName)
//                        Log.d("Network lineNumber", element.lineNumber.toString())
//                    }
//                }
//            })
//    }

//    private val _deleteRecentSearchRecord = MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
//    val deleteRecentSearchRecord: LiveData<MutableList<ResponseRecentSearchRecord.Data>>
//        get() = _deleteRecentSearchRecord

//    fun requestDeleteRecentSearchRecord() {
//        idSearchRepository.deleteRecentSearchRecord(
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiaWF0IjoxNjEwMjkwNzgyLCJleHAiOjE2NDE4MjY3ODIsImlzcyI6ImJlbWUifQ.PflJxm_WRMtgjFYtw68aFNNkkEZWNSuT_2kpgfWCNbY",
//            searchedId = ).enqueue(object : Callback<ResponseDeleteRecentSearchRecord> {
//            override fun onResponse(
//                call: Call<ResponseDeleteRecentSearchRecord>,
//                response: Response<ResponseDeleteRecentSearchRecord>
//            ) {
//                if (response.isSuccessful)
//                    _deleteRecentSearchRecord.value = _deleteRecentSearchRecord.value
//            }
//
//            override fun onFailure(call: Call<ResponseDeleteRecentSearchRecord>, t: Throwable) {
//                Log.d("Network Fail", t.message.toString())
//                for (element in t.stackTrace) {
//                    Log.d("Network element", element.toString())
//                    Log.d("Network className", element.className)
//                    Log.d("Network methodName", element.methodName)
//                    Log.d("Network fileName", element.fileName)
//                    Log.d("Network lineNumber", element.lineNumber.toString())
//                }
//
//            }
//        })
//    }
