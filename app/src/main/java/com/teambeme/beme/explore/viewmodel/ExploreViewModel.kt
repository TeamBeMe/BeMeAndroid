package com.teambeme.beme.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.ResponseExplorationQuestionForFirstAnswer
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.model.ResponseExplorationScrap
import com.teambeme.beme.explore.repository.ExploreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(private val exploreRepository: ExploreRepository) : ViewModel() {
    private var _userNickname: String = ""
    val userNickname: String
        get() = _userNickname

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
    val chipChecked: MutableList<Boolean>
        get() = _chipChecked

    private var _categoryNum: Int? = null
    val categoryNum: Int?
        get() = _categoryNum

    private var _sortingText: String = "최신"
    val sortingText: String
        get() = _sortingText

    private var _page: Int = 2
    val page: Int
        get() = _page

    private var _isMorePage = MutableLiveData(false)
    val isMorePage: LiveData<Boolean>
        get() = _isMorePage

    private var otherAnswersQuestionsID: Int = 0

    private var _tempPage: Int = 1
    val tempPage: Int
        get() = _tempPage

    fun setPageAtRefresh() {
        _page = 2
    }

    fun setCategoryNum(category: Int) {
        clearTempOtherQuestionsList()
        _page = 2
        chipChecked[category - 1] = !chipChecked[category - 1]
        if (chipChecked == listOf(false, false, false, false, false, false)) {
            _categoryNum = null
        } else {
            _chipChecked = mutableListOf(false, false, false, false, false, false)
            chipChecked[category - 1] = !chipChecked[category - 1]
            _categoryNum = category
        }
        requestOtherQuestionsWithCategorySorting(_categoryNum, _sortingText, tempPage)
    }

    fun clearTempOtherQuestionsList() {
        tempOtherQuestionsList?.clear()
    }

    fun clearTempSameQuestionOtherAnswersList() {
        tempSameQuestionOtherAnswersList?.clear()
    }

    fun requestOtherQuestions() {
        var a = 1
        Log.d("DeleteList", "${a++}" + "번 original")
        exploreRepository.getExplorationOtherQuestions(
            _page,
            null,
            "최신"
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        Log.d("abc", "통신 성공")
                        if (response.isSuccessful) {
                            tempOtherQuestionsList =
                                response.body()!!.data.answers.toMutableList()
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

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

    fun requestOtherQuestionsWithCategorySorting(
        category: Int?,
        sorting: String,
        pageNum: Int
    ) {
        exploreRepository.getExplorationOtherQuestions(
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
                            Log.d(
                                "recursion",
                                "pageNum : " + pageNum + " page : " + page + " tempPage : " + tempPage
                            )
                            if (pageNum != page) {
                                if (tempPage == 1) {
                                    clearTempOtherQuestionsList()
                                }
                                response.body()!!.data.answers.toMutableList().let {
                                    tempOtherQuestionsList?.addAll(
                                        it
                                    )
                                }
                                _tempPage++
                                if (response.body()!!.data.answers.isNotEmpty()) {
                                    _isMorePage.value = response.body()!!.data.answers.size == 10
                                } else {
                                    _isMorePage.value = false
                                }
                                requestOtherQuestionsWithCategorySorting(
                                    category,
                                    sorting,
                                    tempPage
                                )
                            } else {
                                _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
                                _tempPage = 1
                            }
                            Log.d(
                                "recursion",
                                " tempPage : " + tempPage
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
            _page,
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
                            tempOtherQuestionsList?.addAll(response.body()!!.data.answers.toMutableList())
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (response.body()!!.data.answers.size == 10) {
                                _page++
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

    fun requestSameQuestionsOtherAnswers(questionId: Int, pageNum: Int, sorting: String = "최신") {
        otherAnswersQuestionsID = questionId
        exploreRepository.getExplorationSameQuestionOtherAnswers(
            otherAnswersQuestionsID,
            pageNum,
            sorting
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        Log.d(
                            "recursion_detail",
                            "pageNum : " + pageNum + " page : " + page + " tempPage : " + tempPage
                        )
                        if (pageNum != page) {
                            if (tempPage == 1) {
                                clearTempSameQuestionOtherAnswersList()
                            }
                            tempSameQuestionOtherAnswersList?.addAll(response.body()!!.data.answers.toMutableList())
                            _tempPage++
                            _isMorePage.value = response.body()!!.data.answers.size == 10
                            requestSameQuestionsOtherAnswers(
                                otherAnswersQuestionsID,
                                tempPage,
                                sorting
                            )
                        } else {
                            _sameQuestionOtherAnswersList.value =
                                tempSameQuestionOtherAnswersList?.toMutableList()
                            _tempPage = 1
                        }
                        Log.d(
                            "recursion_detail",
                            " tempPage : " + tempPage
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
            _page,
            _sortingText
        ).enqueue(
            object : Callback<ResponseExplorationQuestions> {
                override fun onResponse(
                    call: Call<ResponseExplorationQuestions>,
                    response: Response<ResponseExplorationQuestions>
                ) {
                    if (response.isSuccessful) {
                        tempSameQuestionOtherAnswersList?.addAll(response.body()!!.data.answers.toMutableList())
                        _sameQuestionOtherAnswersList.value =
                            tempSameQuestionOtherAnswersList?.toMutableList()

                        if (response.body()!!.data.answers.size == 10) {
                            _page++
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