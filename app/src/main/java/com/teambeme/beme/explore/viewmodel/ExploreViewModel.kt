package com.teambeme.beme.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.data.repository.ExploreRepository
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val exploreRepository: ExploreRepository
) : ViewModel() {
    var userNickname: String = ""
        private set

    private var tempOtherQuestionsList: MutableList<ResponseExplorationQuestions.Data.Answer?>? =
        mutableListOf()
    private val _otherQuestionsList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer?>>()
    val otherQuestionsList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer?>>
        get() = _otherQuestionsList

    private var tempSameQuestionOtherAnswersList: MutableList<ResponseExplorationQuestions.Data.Answer?>? =
        mutableListOf()
    private val _sameQuestionOtherAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer?>>()
    val sameQuestionOtherAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer?>>
        get() = _sameQuestionOtherAnswersList

    private var _questionForFirstAnswer =
        MutableLiveData<ResponseExplorationQuestionForFirstAnswer.Answer>()
    val questionForFirstAnswer: LiveData<ResponseExplorationQuestionForFirstAnswer.Answer>
        get() = _questionForFirstAnswer

    private var _scrapData = ResponseExplorationScrap("", 0, true)
    val scrapData: ResponseExplorationScrap
        get() = _scrapData

    private var _chipChecked = mutableListOf(false, false, false, false, false, false)
    private val chipChecked: MutableList<Boolean>
        get() = _chipChecked

    var categoryNum: Int? = null
        private set

    var page: Int = 2
        private set

    private var _isMorePage = MutableLiveData(false)
    val isMorePage: LiveData<Boolean>
        get() = _isMorePage

    private var otherAnswersQuestionsID: Int = 0

    var tempPage: Int = 1
        private set

    fun setPageAtRefresh() {
        page = 2
    }

    fun setCategoryNum(category: Int) {
        clearTempOtherQuestionsList()
        page = 2
        chipChecked[category - 1] = !chipChecked[category - 1]
        if (chipChecked == listOf(false, false, false, false, false, false)) {
            categoryNum = null
        } else {
            _chipChecked = mutableListOf(false, false, false, false, false, false)
            chipChecked[category - 1] = !chipChecked[category - 1]
            categoryNum = category
        }
        requestOtherQuestionsWithCategorySorting(categoryNum, tempPage)
    }

    fun clearTempOtherQuestionsList() {
        tempOtherQuestionsList?.clear()
    }

    fun clearTempSameQuestionOtherAnswersList() {
        tempSameQuestionOtherAnswersList?.clear()
    }

    fun requestOtherQuestionsWithCategorySorting(
        category: Int?,
        pageNum: Int
    ) {
        exploreRepository.getExplorationOtherQuestions(
            pageNum,
            category
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(
                                "recursion",
                                "pageNum : $pageNum, page : $page, tempPage : $tempPage"
                            )
                            val responseData = requireNotNull(response.body())
                            if (pageNum != page) {
                                if (tempPage == 1) {
                                    clearTempOtherQuestionsList()
                                }
                                tempOtherQuestionsList?.addAll(responseData.data.answers.toMutableList())
                                tempPage++
                                if (responseData.data.answers.isNotEmpty()) {
                                    _isMorePage.value = responseData.data.answers.size == 10
                                } else {
                                    _isMorePage.value = false
                                }
                                requestOtherQuestionsWithCategorySorting(
                                    category,
                                    tempPage
                                )
                            } else {
                                _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
                                tempPage = 1
                            }
                            Log.d(
                                "recursion",
                                " tempPage : $tempPage"
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestOtherQuestionsWithCategorySorting", "통신실패")
                    }
                }
            )
    }

    fun requestPlusOtherQuestions() {
        exploreRepository.getExplorationOtherQuestions(
            page,
            categoryNum
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            val responseData = requireNotNull(response.body())
                            tempOtherQuestionsList?.addAll(responseData.data.answers.toMutableList())
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (responseData.data.answers.size == 10) {
                                page++
                                _isMorePage.value = true
                            } else {
                                _isMorePage.value = false
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestPlusOtherQuestions", "통신실패")
                    }
                }
            )
    }

    fun requestSameQuestionsOtherAnswers(questionId: Int, pageNum: Int) {
        otherAnswersQuestionsID = questionId
        exploreRepository.getExplorationSameQuestionOtherAnswers(
            otherAnswersQuestionsID,
            pageNum
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        Log.d(
                            "recursion_detail",
                            "pageNum : $pageNum, page : $page, tempPage : $tempPage"
                        )
                        val responseData = requireNotNull(response.body())
                        if (pageNum != page) {
                            if (tempPage == 1) {
                                clearTempSameQuestionOtherAnswersList()
                            }
                            tempSameQuestionOtherAnswersList?.addAll(responseData.data.answers.toMutableList())
                            tempPage++
                            _isMorePage.value = responseData.data.answers.size == 10
                            requestSameQuestionsOtherAnswers(
                                otherAnswersQuestionsID,
                                tempPage
                            )
                        } else {
                            _sameQuestionOtherAnswersList.value =
                                tempSameQuestionOtherAnswersList?.toMutableList()
                            tempPage = 1
                        }
                        Log.d(
                            "recursion_detail",
                            " tempPage : $tempPage"
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                    Log.d("network_requestSameQuestionsOtherAnswers", "통신실패")
                }
            }
        )
    }

    fun requestPlusSameQuestionOtherAnswers() {
        exploreRepository.getExplorationSameQuestionOtherAnswers(
            otherAnswersQuestionsID,
            page
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        val responseData = requireNotNull(response.body())
                        tempSameQuestionOtherAnswersList?.addAll(responseData.data.answers.toMutableList())
                        _sameQuestionOtherAnswersList.value =
                            tempSameQuestionOtherAnswersList?.toMutableList()

                        if (responseData.data.answers.size == 10) {
                            page++
                            _isMorePage.value = true
                        } else {
                            _isMorePage.value = false
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                    Log.d("network_requestPlusSameQuestionsOtherAnswers", "통신실패")
                }
            }
        )
    }

    fun requestQuestionForFirstAnswer() {
        exploreRepository.getQuestionForFirstAnswer().enqueue(
            object : Callback<ResponseExplorationQuestionForFirstAnswer> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestionForFirstAnswer>,
                    response: Response<ResponseExplorationQuestionForFirstAnswer>
                ) {
                    if (response.isSuccessful) {
                        _questionForFirstAnswer.value = response.body()!!.data
                    }
                }

                override fun onFailure(
                    call: Call<ResponseExplorationQuestionForFirstAnswer>,
                    t: Throwable
                ) {
                    Log.d("network_requestQuestionForFirstAnswer", "통신실패")
                }
            }
        )
    }

    fun requestScrap(answerId: Int) {
        exploreRepository.putScrap(
            answerId
        ).enqueue(
            object : Callback<ResponseExplorationScrap> {
                override fun onResponse(
                    call: Call<ResponseExplorationScrap>,
                    response: Response<ResponseExplorationScrap>
                ) {
                    if (response.isSuccessful) {
                        val responseData = requireNotNull(response.body())
                        Log.d("scrap_viewmodel", answerId.toString())
                        _scrapData = responseData
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
}
