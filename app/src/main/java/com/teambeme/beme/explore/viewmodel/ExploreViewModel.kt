package com.teambeme.beme.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.repository.ExploreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(private val exploreRepository: ExploreRepository) : ViewModel() {
    private val _otherMindsList = MutableLiveData<List<ResponseExplorationAnswers.Data>>()
    val otherMindsList: LiveData<List<ResponseExplorationAnswers.Data>>
        get() = _otherMindsList

    private var tempOtherQuestionsList: MutableList<ResponseExplorationQuestions.Data.Answer> =
        mutableListOf()

    private val _otherQuestionsList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val otherQuestionsList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _otherQuestionsList

    private var _chipChecked = mutableListOf(false, false, false, false, false, false)
    val chipChecked: MutableList<Boolean>
        get() = _chipChecked

    private var categoryNum: Int? = null

    private var sortingText: String = "최신"

    private var page: Int = 1

    private var _isMaxPage = false
    val isMaxPage: Boolean
        get() = _isMaxPage

    fun setCategoryNum(category: Int) {
        page = 1
        _isMaxPage = false
        chipChecked[category - 1] = !chipChecked[category - 1]
        if (chipChecked == listOf(false, false, false, false, false, false)) {
            categoryNum = null
        } else {
            _chipChecked = mutableListOf(false, false, false, false, false, false)
            chipChecked[category - 1] = !chipChecked[category - 1]
            categoryNum = category
        }
        requestOtherQuestionsWithCategorySorting(categoryNum, sortingText)
    }

    fun setSortingText(sorting: String) {
        page = 1
        _isMaxPage = false
        sortingText = sorting
        requestOtherQuestionsWithCategorySorting(categoryNum, sortingText)
    }

    private val _otherAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val otherAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _otherAnswersList

    fun requestOtherMinds() {
        exploreRepository.getExplorationAnother("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI")
            .enqueue(
                object : Callback<ResponseExplorationAnswers> {
                    override fun onResponse(
                        call: Call<ResponseExplorationAnswers>,
                        response: Response<ResponseExplorationAnswers>
                    ) {
                        if (response.isSuccessful)
                            _otherMindsList.value = response.body()!!.data?.toList()
                    }

                    override fun onFailure(call: Call<ResponseExplorationAnswers>, t: Throwable) {
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
                            _otherQuestionsList.value = tempOtherQuestionsList.toMutableList()
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

    fun requestOtherQuestionsWithCategorySorting(category: Int?, sorting: String) {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page,
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
                            _otherQuestionsList.value =
                                response.body()!!.data?.answers?.toMutableList()
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

    fun requestPlusOtherQuestions() {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            page,
            categoryNum,
            sortingText
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            tempOtherQuestionsList.addAll(response.body()!!.data?.answers?.toMutableList())
                            _otherQuestionsList.value = tempOtherQuestionsList.toMutableList()

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
}