package com.teambeme.beme.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.ResponseExplorationMinds
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.explore.repository.ExploreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(private val exploreRepository: ExploreRepository) : ViewModel() {
    private val _otherMindsList = MutableLiveData<List<ResponseExplorationMinds.Data>>()
    val otherMindsList: LiveData<List<ResponseExplorationMinds.Data>>
        get() = _otherMindsList

    private var tempOtherQuestionsList: MutableList<ResponseExplorationQuestions.Data.Answer>? =
        mutableListOf()
    private val _otherQuestionsList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val otherQuestionsList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _otherQuestionsList

    private var tempSameQuestionOtherAnswersList: MutableList<ResponseExplorationQuestions.Data.Answer>? =
        mutableListOf()
    private val _sameQuestionOtherAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val sameQuestionOtherAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _sameQuestionOtherAnswersList

    private var _questionForFirstAnswer =
        ResponseExplorationQuestionForFirstAnswer.Answer("", 0, 0, "")
    val questionForFirstAnswer: ResponseExplorationQuestionForFirstAnswer.Answer
        get() = _questionForFirstAnswer

    private var _scrapData = ResponseExplorationScrap("",0,true)
    val scrapData: ResponseExplorationScrap
        get() = _scrapData

    private var _chipChecked = mutableListOf(false, false, false, false, false, false)
    val chipChecked: MutableList<Boolean>
        get() = _chipChecked

    private var _categoryNum: Int? = null
    val categoryNum: Int?
        get() = _categoryNum

    private var _sortingText: String = "최신"
    val sortingText: String
        get() = _sortingText

    private var page: Int = 1

    private var _isMaxPage = false
    val isMaxPage: Boolean
        get() = _isMaxPage

    private var otherAnswersQuestionsID: Int = 0

    fun setCategoryNum(category: Int) {
        page = 1
        _isMaxPage = false
        chipChecked[category - 1] = !chipChecked[category - 1]
        if (chipChecked == listOf(false, false, false, false, false, false)) {
            _categoryNum = null
        } else {
            _chipChecked = mutableListOf(false, false, false, false, false, false)
            chipChecked[category - 1] = !chipChecked[category - 1]
            _categoryNum = category
        }
        requestOtherQuestionsWithCategorySorting(_categoryNum, _sortingText)
    }

    fun setSortingTextFromExplore(sorting: String) {
        page = 1
        _isMaxPage = false
        _sortingText = sorting
        requestOtherQuestionsWithCategorySorting(_categoryNum, _sortingText)
    }

    fun setSortingTextFromExploreDetail(questionId: Int, sorting: String) {
        page = 1
        _isMaxPage = false
        _sortingText = sorting
        requestSameQuestionsOtherAnswers(questionId, sorting)
    }

    fun requestOtherMinds() {
        exploreRepository.getExplorationAnother("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI")
            .enqueue(
                object : Callback<ResponseExplorationMinds> {
                    override fun onResponse(
                        call: Call<ResponseExplorationMinds>,
                        response: Response<ResponseExplorationMinds>
                    ) {
                        if (response.isSuccessful)
                            _otherMindsList.value = response.body()!!.data?.toList()
                    }

                    override fun onFailure(call: Call<ResponseExplorationMinds>, t: Throwable) {
                        Log.d("network_requestOtherMinds", "통신실패")
                    }
                }
            )
    }

    fun requestOtherQuestions() {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page,
            null,
            "최신"
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            tempOtherQuestionsList =
                                response.body()!!.data?.answers?.toMutableList()
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
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

    fun requestOtherQuestionsWithCategorySorting(category: Int?, sorting: String, pageNum: Int = page) {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            pageNum,
            category,
            sorting
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            page = pageNum
                            tempOtherQuestionsList =
                                response.body()!!.data?.answers?.toMutableList()
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
                            if (response.body()!!.data?.pageLen == page) {
                                _isMaxPage = true
                            } else {
                                page++
                            }
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
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page,
            _categoryNum,
            _sortingText
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            tempOtherQuestionsList?.addAll(response.body()!!.data?.answers?.toMutableList())
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (response.body()!!.data?.pageLen == page) {
                                _isMaxPage = true
                            } else {
                                page++
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestPlusOtherQuestions", "통신실패")
                    }
                }
            )
    }

    fun requestSameQuestionsOtherAnswers(questionId: Int, sorting: String = "최신") {
        page = 1
        _isMaxPage = false
        otherAnswersQuestionsID = questionId
        exploreRepository.getExplorationSameQuestionOtherAnswers(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            otherAnswersQuestionsID,
            page,
            sorting
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        tempSameQuestionOtherAnswersList =
                            response.body()!!.data?.answers?.toMutableList()
                        _sameQuestionOtherAnswersList.value =
                            tempSameQuestionOtherAnswersList?.toMutableList()
                        Log.d(
                            "network_sameQuestionsOtherAnswers",
                            otherAnswersQuestionsID.toString()
                        )
                        if (response.body()!!.data?.pageLen == page) {
                            _isMaxPage = true
                        } else {
                            page++
                        }
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
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            otherAnswersQuestionsID,
            page,
            _sortingText
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        tempSameQuestionOtherAnswersList?.addAll(response.body()!!.data?.answers?.toMutableList())
                        _sameQuestionOtherAnswersList.value =
                            tempSameQuestionOtherAnswersList?.toMutableList()

                        if (response.body()!!.data?.pageLen == page) {
                            _isMaxPage = true
                        } else {
                            page++
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
        exploreRepository.getQuestionForFirstAnswer(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI"
        ).enqueue(
            object : Callback<ResponseExplorationQuestionForFirstAnswer> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestionForFirstAnswer>,
                    response: Response<ResponseExplorationQuestionForFirstAnswer>
                ) {
                    if (response.isSuccessful) {
                        _questionForFirstAnswer = response.body()!!.data
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

    fun requestScrap(answerId: Int, answerData:ResponseExplorationQuestions.Data.Answer){
        exploreRepository.putScrap(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
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
}