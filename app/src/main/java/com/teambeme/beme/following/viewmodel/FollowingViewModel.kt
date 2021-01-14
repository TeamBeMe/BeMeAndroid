package com.teambeme.beme.following.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.following.model.RequestFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingFollow
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import com.teambeme.beme.following.repository.FollowingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel(private val followingRepository: FollowingRepository) : ViewModel() {
    private var tempFollowingFollowerAnswersList: MutableList<ResponseExplorationQuestions.Data.Answer>? =
        mutableListOf()
    private val _followingAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val followingAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _followingAnswersList

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

    private var _isFollowing: String = ""
    val isFollowing: String
        get() = _isFollowing

    private var _page: Int = 1
    val page: Int
        get() = _page

    private var _maxPage: Int = 0
    val maxPage: Int
        get() = _maxPage

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    private var searchQuery: String = ""
    fun setSearchQuery(query: String) {
        searchQuery = query
    }

    private var searchRange: String = followeeCategory
    fun setSearchRange(range: String) {
        searchRange = range
    }

    fun deleteSearchRecord() {
        tempSearchList = mutableListOf(
            ResponseFollowingSearchId.Data(0, null, "", "")
        )
        _searchList.value = tempSearchList?.toMutableList()
    }

    fun requestFollowingFollowerAnswers(pageNum: Int = _page) {
        followingRepository.getFollowingAnswers(
            pageNum
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        _page = pageNum
                        if(_userNickname.value == null){
                            _userNickname.value = response.body()!!.data.userNickname
                        }
                        if(_maxPage == 0){
                            _maxPage = response.body()!!.data?.pageLen
                        }
                        tempFollowingFollowerAnswersList =
                            response.body()!!.data?.answers?.toMutableList()
                        _followingAnswersList.value =
                            tempFollowingFollowerAnswersList?.toMutableList()

                        Log.d("abcabc", "${followingList.value?.size}")

                        if (response.body()!!.data?.pageLen != _page) {
                            _page++
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
        followingRepository.getFollowingAnswers(
            _page
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        response.body()!!.data?.answers?.toMutableList()?.let {
                            tempFollowingFollowerAnswersList?.addAll(
                                it
                            )
                        }
                        _followingAnswersList.value =
                            tempFollowingFollowerAnswersList?.toMutableList()
                        if (response.body()!!.data?.pageLen != _page) {
                            _page++
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
        followingRepository.getFollowingFollowerList().enqueue(
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
        followingRepository.getSearchMyFollowingFollower(
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
                        if (tempSearchList == null) {
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

    fun requestFollow(userId: Int){
        followingRepository.putFollow(
            RequestFollowingFollow(userId)
        ).enqueue(
            object : Callback<ResponseFollowingFollow> {
                override fun onResponse(
                    call: Call<ResponseFollowingFollow>,
                    response: Response<ResponseFollowingFollow>
                ) {
                    Log.d("network_requestSearch", "통신성")
                    if (response.isSuccessful) {
                        _isFollowing = response.body()!!.message
                    }
                }

                override fun onFailure(call: Call<ResponseFollowingFollow>, t: Throwable) {
                    Log.d("network_requestSearch", "통신실패")
                }
            }
        )
    }

    companion object {
        private const val followeeCategory: String = "followee"
    }
}