package com.teambeme.beme.idsearchfollowing.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearch
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.repository.IdSearchRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IdSearchViewModel(private val idSearchRepository: IdSearchRepository) : ViewModel() {
    val idSearchText = MutableLiveData<String>()
//    val searchedId = ""

    private val _idSearch = MutableLiveData<ResponseIdSearch.Data>()
    val idSearch: LiveData<ResponseIdSearch.Data>
        get() = _idSearch

    fun requestIdSearchResult() {
        idSearchRepository.idSearch(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiaWF0IjoxNjEwMjkwNzgyLCJleHAiOjE2NDE4MjY3ODIsImlzcyI6ImJlbWUifQ.PflJxm_WRMtgjFYtw68aFNNkkEZWNSuT_2kpgfWCNbY",
            idSearchText.value ?: "", ""
        ).enqueue(object : Callback<ResponseIdSearch> {
            override fun onResponse(
                call: Call<ResponseIdSearch>,
                response: Response<ResponseIdSearch>
            ) {
                if (response.isSuccessful)
                    _idSearch.value = response.body()!!.data
            }

            override fun onFailure(call: Call<ResponseIdSearch>, t: Throwable) {
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

    private val _getRecentSearchRecord =
        MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
    val getRecentSearchRecord: LiveData<MutableList<ResponseRecentSearchRecord.Data>>
        get() = _getRecentSearchRecord

    fun requestGetRecentSearchRecord() {
        idSearchRepository.getRecentSearchRecord(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NSwiaWF0IjoxNjEwMjkwNzgyLCJleHAiOjE2NDE4MjY3ODIsImlzcyI6ImJlbWUifQ.PflJxm_WRMtgjFYtw68aFNNkkEZWNSuT_2kpgfWCNbY"
        )
            .enqueue(object : Callback<ResponseRecentSearchRecord> {
                override fun onResponse(
                    call: Call<ResponseRecentSearchRecord>,
                    response: Response<ResponseRecentSearchRecord>
                ) {
                    if (response.isSuccessful)
                        _getRecentSearchRecord.value = response.body()!!.data?.toMutableList()
                }

                override fun onFailure(call: Call<ResponseRecentSearchRecord>, t: Throwable) {
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
}