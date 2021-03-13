package com.teambeme.beme.following.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.following.model.*
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

    private var _answerData = MutableLiveData<ResponseFollowingAnswer.Data>()
    val answerData: LiveData<ResponseFollowingAnswer.Data>
        get() = _answerData

    private var _page: Int = 2
    val page: Int
        get() = _page

    private var _tempPage: Int = 1
    val tempPage: Int
        get() = _tempPage

    private var _isMorePage = MutableLiveData(false)
    val isMorePage: LiveData<Boolean>
        get() = _isMorePage

    private var _scrapData = ResponseExplorationScrap("", 0, true)
    val scrapData: ResponseExplorationScrap
        get() = _scrapData

    // myNickname LiveData 사용한 이유 : 답변하고 확인하기 버튼 때문!
    // 답변하고 돌아오면 item layout 바뀌어야 하므
    private val _myNickname = MutableLiveData<String>()
    val myNickname: LiveData<String>
        get() = _myNickname

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

    fun setPageAtRefresh() {
        _page = 2
    }

    fun clearTempFollowingFollowerAnswerList() {
        tempFollowingFollowerAnswersList?.clear()
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
                        Log.d(
                            "recursion_following",
                            "pageNum : $pageNum, page : $page, tempPage : $tempPage"
                        )
                        if (pageNum != page) {
                            if (tempPage == 1) {
                                clearTempFollowingFollowerAnswerList()
                            }
                            if (_myNickname.value == null) {
                                _myNickname.value = response.body()!!.data.userNickname
                            }
                            tempFollowingFollowerAnswersList?.addAll(response.body()!!.data.answers.toMutableList())
                            _tempPage++
                            if (response.body()!!.data.answers.isNotEmpty()) {
                                _isMorePage.value = response.body()!!.data.answers.size == 10
                            } else {
                                _isMorePage.value = false
                            }
                            requestFollowingFollowerAnswers(
                                tempPage
                            )
                        } else {
                            _followingAnswersList.value =
                                tempFollowingFollowerAnswersList?.toMutableList()
                            _tempPage = 1
                        }
                        Log.d(
                            "recursion_following",
                            " tempPage : $tempPage"
                        )
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
                        tempFollowingFollowerAnswersList?.addAll(response.body()!!.data.answers.toMutableList())
                        _followingAnswersList.value =
                            tempFollowingFollowerAnswersList?.toMutableList()
                        if (response.body()!!.data.answers.size == 10) {
                            _page++
                            _isMorePage.value = true
                        } else {
                            _isMorePage.value = false
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
                        _followerList.value = response.body()!!.data.followers.toMutableList()
                        _followingList.value = response.body()!!.data.followees.toMutableList()
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

    fun requestFollow(userId: Int) {
        followingRepository.putFollow(
            RequestFollowingFollow(userId)
        ).enqueue(
            object : Callback<ResponseFollowingFollow> {
                override fun onResponse(
                    call: Call<ResponseFollowingFollow>,
                    response: Response<ResponseFollowingFollow>
                ) {
                }

                override fun onFailure(call: Call<ResponseFollowingFollow>, t: Throwable) {
                    Log.d("network_requestSearch", "통신실패")
                }
            }
        )
    }

    fun requestDeleteFollower(userId: Int) {
        followingRepository.deleteFollow(userId).enqueue(
            object : Callback<ResponseFollowingFollow> {
                override fun onResponse(
                    call: Call<ResponseFollowingFollow>,
                    response: Response<ResponseFollowingFollow>
                ) {
                }

                override fun onFailure(call: Call<ResponseFollowingFollow>, t: Throwable) {
                    Log.d("network_requestSearch", "통신실패")
                }
            }
        )
    }

    fun requestScrap(answerId: Int) {
        followingRepository.putScrap(
            answerId
        ).enqueue(
            object : Callback<ResponseExplorationScrap> {
                override fun onResponse(
                    call: Call<ResponseExplorationScrap>,
                    response: Response<ResponseExplorationScrap>
                ) {
                    if (response.isSuccessful) {
                        Log.d("scrap_viewmodel", answerId.toString())
                        _scrapData = response.body()!!
                        Log.d("scrap_1", scrapData.message)
                    }
                }

                override fun onFailure(
                    call: Call<ResponseExplorationScrap>,
                    t: Throwable
                ) {
                    Log.d("network_requestQuestionForFirstAnswer", "통신실패")
                }
            }
        )
    }

    fun requestAnswer(answer: RequestFollowingAnswer) {
        followingRepository.postAnswer(
            answer
        ).enqueue(
            object : Callback<ResponseFollowingAnswer> {
                override fun onResponse(
                    call: Call<ResponseFollowingAnswer>,
                    response: Response<ResponseFollowingAnswer>
                ) {
                    if (response.isSuccessful) {
                        Log.d("answer", "viewmodel")
                        _answerData.value = response.body()!!.data
                    }
                }

                override fun onFailure(
                    call: Call<ResponseFollowingAnswer>,
                    t: Throwable
                ) {
                    Log.d("network_requestQuestionForFirstAnswer", "통신실패")
                }
            }
        )
    }

    companion object {
        private const val followeeCategory: String = "followee"
    }
}