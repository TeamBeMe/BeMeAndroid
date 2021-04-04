package com.teambeme.beme.idsearchfollowing.viewmodel

import androidx.lifecycle.*
import com.teambeme.beme.data.repository.IdSearchRepository
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdSearchViewModel @Inject constructor(
    private val idSearchRepository: IdSearchRepository
) : ViewModel() {
    private var copyRecentSearchList: MutableList<ResponseRecentSearchRecord.Data> = mutableListOf()

    private val _recentSearchData = MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>()
    val recentSearchData: MutableLiveData<MutableList<ResponseRecentSearchRecord.Data>>
        get() = _recentSearchData

    private var tempIdSearchList: MutableList<ResponseIdSearchData.Data>? = mutableListOf()

    private val _idSearchData = MutableLiveData<MutableList<ResponseIdSearchData.Data>>()
    val idSearchData: LiveData<MutableList<ResponseIdSearchData.Data>>
        get() = _idSearchData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    @ExperimentalCoroutinesApi
    val searchQuery = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val searchResult = searchQuery.asFlow()
        .debounce(500)
        .distinctUntilChanged()
        .mapLatest { query ->
            idSearchRepository.idSearch(query, "all").data.let { mutableListOf(it) }
        }.catch {
            _errorMessage.value = "검색 중 네트워크 오류가 발생했습니다."
        }.asLiveData()

//    private var searchQuery: String = ""
//    fun setSearchQuery(query: String) {
//        searchQuery = query
//    }

    private
    val _deletePosition = MutableLiveData<Int>()
    val deletePosition: LiveData<Int>
        get() = _deletePosition

    private var _isFollowed = MutableLiveData(false)
    val isFollowed: LiveData<Boolean?>
        get() = _isFollowed

    fun deleteSearchRecord() {
        tempIdSearchList = mutableListOf(
            ResponseIdSearchData.Data(0, null, "", "")
        )
        _idSearchData.value = tempIdSearchList?.toMutableList()
    }

    fun requestRecentSearchData() {
        viewModelScope.launch {
            runCatching { idSearchRepository.getRecentSearchRecord() }
                .onSuccess {
                    copyRecentSearchList = it.data?.toMutableList() ?: mutableListOf()
                    _recentSearchData.value = copyRecentSearchList.toMutableList()
                }
                .onFailure { it.printStackTrace() }
        }
//        idSearchRepository.getRecentSearchRecord()
//            .enqueue(
//                object : Callback<ResponseRecentSearchRecord> {
//                    override fun onResponse(
//                        call: Call<ResponseRecentSearchRecord>,
//                        response: Response<ResponseRecentSearchRecord>
//                    ) {
//                        if (response.isSuccessful) {
//                            Log.d("Network is success", response.body().toString())
//                            copyRecentSearchList =
//                                response.body()!!.data?.toMutableList() ?: mutableListOf()
//                            _recentSearchData.value = copyRecentSearchList.toMutableList()
//                        } else {
//                            Log.d("Network Error", response.body()?.data.toString())
//                            Log.d("Network Error", response.body()?.status.toString())
//                            Log.d("Network Error", response.body()?.success.toString())
//                            Log.d("Network Error", response.message())
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseRecentSearchRecord>, t: Throwable) {
//                        Log.d("network_requestOtherMinds", "통신실패")
//                    }
//                }
//            )
    }

    fun setPosition(position: Int) {
        _deletePosition.value = position
    }

    fun deleteRecentSearch() {
        viewModelScope.launch {
            runCatching {
                idSearchRepository.deleteRecentSearchRecord(
                    copyRecentSearchList[deletePosition.value!!].id
                )
            }.onFailure {
                _errorMessage.value = "삭제 시 네트워크 오류가 발생했습니다."
            }
        }
//        idSearchRepository.deleteRecentSearchRecord(
//            copyRecentSearchList[deletePosition.value!!].id
//        ).enqueue(object : Callback<ResponseDeleteRecentSearchRecord> {
//            override fun onResponse(
//                call: Call<ResponseDeleteRecentSearchRecord>,
//                response: Response<ResponseDeleteRecentSearchRecord>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("Network Fail", response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseDeleteRecentSearchRecord>, t: Throwable) {
//                Log.d("Network Fail", t.message.toString())
//            }
//        })
    }

    fun requestIdSearchData() {
//        viewModelScope.launch {
//            runCatching { idSearchRepository.idSearch(searchQuery, "all") }
//                .onSuccess { _idSearchData.value = it.data?.let { mutableListOf(it) } }
//                .onFailure { _errorMessage.value = "검색 중 네트워크 오류가 발생했습니다." }
//        }
//        idSearchRepository.idSearch(
//            searchQuery, "all"
//        )
//            .enqueue(
//                object : Callback<ResponseIdSearchData> {
//                    override fun onResponse(
//                        call: Call<ResponseIdSearchData>,
//                        response: Response<ResponseIdSearchData>
//                    ) {
//                        if (response.isSuccessful) {
//                            Log.d("Network is success", response.body().toString())
//                            tempIdSearchList = response.body()!!.data?.let { mutableListOf(it) }
//                            _idSearchData.value = tempIdSearchList?.toMutableList()
//                            if (tempIdSearchList == null || tempIdSearchList?.size == 0) {
//                                deleteSearchRecord()
//                            }
//                        } else {
//                            Log.d("Network Error", response.body()?.data.toString())
//                            Log.d("Network Error", response.body()?.status.toString())
//                            Log.d("Network Error", response.body()?.success.toString())
//                            Log.d("Network Error", response.message())
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseIdSearchData>, t: Throwable) {
//                        Log.d("network_requestOtherMinds", "통신실패")
//                    }
//                }
//            )
    }

    fun requestFollowAndFollowing(userId: Int) {
        viewModelScope.launch {
            runCatching { idSearchRepository.putFollowAndFollowing(userId) }
                .onFailure { _errorMessage.value = "네트워크 오류가 발생했습니다." }
        }
//        idSearchRepository.putFollowAndFollowing(
//            RequestFollowAndFollowing(userId)
//        ).enqueue(
//            object : Callback<ResponseFollowAndFollowing> {
//                override fun onResponse(
//                    call: Call<ResponseFollowAndFollowing>,
//                    response: Response<ResponseFollowAndFollowing>
//                ) {
//                    Log.d("network_requestSearch", "통신성")
//                    if (response.isSuccessful) {
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseFollowAndFollowing>, t: Throwable) {
//                    Log.d("network_requestSearch", "통신실패")
//                }
//            }
//        )
    }
}