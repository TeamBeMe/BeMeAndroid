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

    private var _userNickname: String = ""
    val userNickname: String
        get() = _userNickname

    private var tempList: MutableList<ResponseExplorationQuestions.Data.Answer?>? =
        mutableListOf()
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

    private var _page: Int = 1
    val page: Int
        get() = _page

    private var _againPage: Int = 1
    val againPage: Int
        get() = _againPage

    private var _isMorePage = MutableLiveData(false)
    val isMorePage: LiveData<Boolean>
        get() = _isMorePage

    private var otherAnswersQuestionsID: Int = 0

    private var _doRequest = MutableLiveData<Boolean>()
    val doRequest: LiveData<Boolean>
        get() = _doRequest

    private var clickedItemPosition = 0

    private var _isClickBookmark = MutableLiveData(false)
    val isClickBookmark: LiveData<Boolean>
        get() = _isClickBookmark

    fun getItemPosition() = clickedItemPosition

    fun setIsClickBookmarkTrue() {
        _isClickBookmark.value = true
    }

    fun setClickedItemPosition(position: Int) {
        clickedItemPosition = position
        Log.d(
            "scrapConnection_viewmodel_position_isScrapped",
            "${otherQuestionsList.value?.get(clickedItemPosition)?.isScrapped}"
        )
    }

    fun setChangeBookmark() {
        //tempList = tempOtherQuestionsList?.toMutableList()
        tempList?.get(clickedItemPosition)?.isScrapped =
            !(tempList?.get(clickedItemPosition)?.isScrapped)!!
//        val isScrap = tempOtherQuestionsList?.get(clickedItemPosition)?.isScrapped
//        tempOtherQuestionsList?.get(clickedItemPosition)?.isScrapped = !isScrap!!

        //_otherQuestionsList.value[clickedItemPosition].isScrapped
        Log.d("scrapConnection_주소값_1", "${otherQuestionsList === tempOtherQuestionsList}")
        Log.d("scrapConnection_주소값_2", "${otherQuestionsList === tempList}")
        Log.d(
            "scrapConnection_viewmodel_setChange_isScrapped1",
            "${otherQuestionsList.value?.get(clickedItemPosition)?.isScrapped}"
        )
        //setOtherList()

        Log.d(
            "scrapConnection_viewmodel_setChange_isScrapped2",
            "${otherQuestionsList.value?.get(clickedItemPosition)?.isScrapped}"
        )
        _isClickBookmark.value = false
    }

    fun setOtherList() {
        _otherQuestionsList.value = tempList?.toMutableList()
        Log.d(
            "scrapConnection_viewmodel_setOtherList_isScrapped",
            "${otherQuestionsList.value?.get(clickedItemPosition)?.isScrapped}"
        )
    }

    fun setDoRequestTrue() {
        _doRequest.value = true
    }

    fun setCategoryNum(category: Int) {
        _page = 1
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
        _page = 1
        _sortingText = sorting
        requestOtherQuestionsWithCategorySorting(_categoryNum, _sortingText)
    }

    fun setSortingTextFromExploreDetail(questionId: Int, sorting: String) {
        _page = 1
        _sortingText = sorting
        requestSameQuestionsOtherAnswers(questionId, sorting)
    }

    fun requestOtherMinds() {
        exploreRepository.getExplorationAnother()
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
                            tempList = response.body()!!.data.answers?.toMutableList()
                            tempOtherQuestionsList =
                                response.body()!!.data?.answers?.toMutableList()
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (response.body()!!.data != null) {
                                if (response.body()!!.data?.pageLen > _page) {
                                    _page++
                                    _isMorePage.value = true
                                } else {
                                    _isMorePage.value = false
                                }
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
        pageNum: Int = _page
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
                            _page = pageNum
                            tempOtherQuestionsList =
                                response.body()!!.data?.answers?.toMutableList()
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
                            if (response.body()!!.data != null) {
                                if (response.body()!!.data?.pageLen > _page) {
                                    _page++
                                    _isMorePage.value = true
                                } else {
                                    _isMorePage.value = false
                                }
                            } else {
                                _isMorePage.value = false
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestOtherQuestionsWithCategorySorting", "통신실패")
                    }
                }
            )
    }

    fun requestAgainOtherQuestions(
        category: Int?,
        sorting: String,
        pageNum: Int
    ) {
        if (pageNum == 1) {
            tempOtherQuestionsList?.clear()
            Log.d("DeleteList", "$tempOtherQuestionsList")
        }
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
                            Log.d("DeleteList", "$pageNum")
                            response.body()!!.data?.answers?.toMutableList()?.let {
                                tempOtherQuestionsList?.addAll(
                                    it
                                )
                            }
//                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (response.body()!!.data != null) {
                                if (response.body()!!.data?.pageLen > againPage) {
                                    _againPage++
                                    _isMorePage.value = true
                                    if (againPage == page) {
                                        _doRequest.value = false
                                        _againPage = 1
                                    } else {
                                        _doRequest.value = true
                                    }
                                } else {
                                    _isMorePage.value = false
                                }
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
        Log.d("DeleteList", "againPage : " + "$againPage")
        Log.d("DeleteList", "page : " + "$page")
        Log.d("DeleteList", "통신 가능 : " + "${doRequest.value}")
    }

    fun setList() {
        _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()
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
                            response.body()!!.data?.answers?.toMutableList()?.let {
                                tempOtherQuestionsList?.addAll(
                                    it
                                )
                            }
                            _otherQuestionsList.value = tempOtherQuestionsList?.toMutableList()

                            if (response.body()!!.data?.pageLen > _page) {
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

    fun requestSameQuestionsOtherAnswers(questionId: Int, sorting: String = "최신") {
        _page = 1
        otherAnswersQuestionsID = questionId
        exploreRepository.getExplorationSameQuestionOtherAnswers(
            otherAnswersQuestionsID,
            _page,
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
                        if (response.body()!!.data?.pageLen > _page) {
                            _page++
                            _isMorePage.value = true
                        } else {
                            _isMorePage.value = false
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
                        response.body()!!.data?.answers?.toMutableList()?.let {
                            tempSameQuestionOtherAnswersList?.addAll(
                                it
                            )
                        }
                        _sameQuestionOtherAnswersList.value =
                            tempSameQuestionOtherAnswersList?.toMutableList()

                        if (response.body()!!.data?.pageLen > _page) {
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

    fun requestScrap(answerId: Int, answerData: ResponseExplorationQuestions.Data.Answer) {
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