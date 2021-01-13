package com.teambeme.beme.following.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.R
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.FollowingProfilesData
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import com.teambeme.beme.following.repository.FollowingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel(private val followingRepository: FollowingRepository) : ViewModel() {
    private var tempFollowingFollowerAnswersList: MutableList<ResponseExplorationQuestions.Data.Answer>? =
        mutableListOf()
    private val _followingFollowerAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val followingFollowerAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _followingFollowerAnswersList

    private val _followerList = MutableLiveData<MutableList<ResponseFollowingList.Data.Follower>>()
    val followerList: LiveData<MutableList<ResponseFollowingList.Data.Follower>>
        get() = _followerList

    private val _followingList = MutableLiveData<MutableList<ResponseFollowingList.Data.Followee>>()
    val followingList: LiveData<MutableList<ResponseFollowingList.Data.Followee>>
        get() = _followingList

    private var tempSearchList: MutableList<ResponseFollowingSearchId.Data>? = mutableListOf()
    private var _searchList = MutableLiveData<MutableList<ResponseFollowingSearchId.Data>>()
    val searchList: LiveData<MutableList<ResponseFollowingSearchId.Data>>
        get() = _searchList

    private var page: Int = 1

    private var _isMaxPage = false
    val isMaxPage: Boolean
        get() = _isMaxPage

    private var searchQuery: String = ""
    fun setSearchQuery(query: String){
        searchQuery = query
    }

    private var searchRange: String = followeeCategory
    fun setSearchRange(range: String){
        searchRange = range
    }

    fun deleteSearchRecord(){
        tempSearchList = mutableListOf(
            ResponseFollowingSearchId.Data(0, null, "", "")
        )
        _searchList.value = tempSearchList?.toMutableList()
    }

    fun requestFollowingFollowerAnswers(pageNum: Int = page) {
        followingRepository.getFollowingFollowerAnswers(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            pageNum,
            followeeCategory
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        page = pageNum
                        tempFollowingFollowerAnswersList =
                            response.body()!!.data?.answers?.toMutableList()
                        _followingFollowerAnswersList.value =
                            tempFollowingFollowerAnswersList?.toMutableList()
                        if (response.body()!!.data?.pageLen == page) {
                            _isMaxPage = true
                        } else {
                            page++
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                    Log.d("network_requestOtherQuestions", "통신실패")
                }
            }
        )
    }

    fun requestPlusFollowingFollowerAnswers() {
        followingRepository.getFollowingFollowerAnswers(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page,
            followeeCategory
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        tempFollowingFollowerAnswersList?.addAll(response.body()!!.data?.answers?.toMutableList())
                        _followingFollowerAnswersList.value =
                            tempFollowingFollowerAnswersList?.toMutableList()
                        if (response.body()!!.data?.pageLen == page) {
                            _isMaxPage = true
                        } else {
                            page++
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                    Log.d("network_requestOtherQuestions", "통신실패")
                }
            }
        )
    }

    fun requestFollowerFollowingList() {
        followingRepository.getFollowingFollowerList(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI"
        ).enqueue(
            object : Callback<ResponseFollowingList> {
                override fun onResponse(
                    call: Call<ResponseFollowingList>,
                    response: Response<ResponseFollowingList>
                ) {
                    if (response.isSuccessful) {
                        _followerList.value = response.body()!!.data?.followers?.toMutableList()
                        _followingList.value = response.body()!!.data?.followees?.toMutableList()
                    }
                }

                override fun onFailure(call: Call<ResponseFollowingList>, t: Throwable) {
                    Log.d("network_requestOtherQuestions", "통신실패")
                }
            }
        )
    }

    fun requestSearchMyFollowingFollower() {
        Log.d("search______", searchList.toString())
        followingRepository.getSearchMyFollowingFollower(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            searchQuery,
            searchRange
        ).enqueue(
            object : Callback<ResponseFollowingSearchId> {
                override fun onResponse(
                    call: Call<ResponseFollowingSearchId>,
                    response: Response<ResponseFollowingSearchId>
                ) {
                    Log.d("network_requestSearch", "통신성")
                    if (response.isSuccessful) {
                        tempSearchList = response.body()!!.data?.let { mutableListOf(it) }
                        _searchList.value = tempSearchList?.toMutableList()
                        if(tempSearchList == null){
                            deleteSearchRecord()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseFollowingSearchId>, t: Throwable) {
                    Log.d("network_requestSearch", "통신실패")
                }
            }
        )
    }

    companion object {
        private const val followeeCategory: String = "followee"
    }
}