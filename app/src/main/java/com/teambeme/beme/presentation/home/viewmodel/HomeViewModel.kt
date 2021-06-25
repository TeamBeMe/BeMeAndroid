package com.teambeme.beme.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.teambeme.beme.domain.repository.HomeRepository
import com.teambeme.beme.presentation.home.model.Answer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _answerList = MutableLiveData<MutableList<Answer>>()
    val answerList: LiveData<MutableList<Answer>>
        get() = _answerList
    private var _currentQuestionPage = 1
    private var canAdd = true

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String>
        get() = _successMessage
    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _returnToStartEvent = MutableLiveData<Boolean>()
    val returnToStartEvent: LiveData<Boolean>
        get() = _returnToStartEvent

    fun loadAnswers(): Flow<PagingData<Answer>> {
        return homeRepository.retrieveAnswerPages()
            .cachedIn(viewModelScope)
    }

    fun getMoreAnswers() {
        viewModelScope.launch {
            Log.d("OkHome", "getMoreAnswer")
            try {
                if (canAdd) {
                    val moreAnswers =
                        homeRepository.getAnswers(++_currentQuestionPage).answers.toMutableList()
                    moreAnswers.reverse()
                    val currentList = _answerList.value?.toMutableList()
                    if (currentList != null) {
                        moreAnswers.addAll(currentList)
                    }
                    _answerList.value = moreAnswers.toMutableList()
                    Log.d("OkHome", "getMoreAnswerCanAddSuccess")
                } else {
                    _errorMessage.value = "더 이상 페이지가 없습니다"
                }
                Log.d("OkHome", "getMoreAnswerSuccess")
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    _errorMessage.value = "더 이상 페이지가 없습니다"
                    _currentQuestionPage--
                    Log.d("OkHome", "$_currentQuestionPage")
                    canAdd = false
                    Log.d("OkHome", "getMoreAnswerFailure")
                } else {
                    _currentQuestionPage--
                    _errorMessage.value = "서버 통신에 문제가 발생했습니다"
                }
            }
        }
    }

    fun refreshTaskCompleted() {
        viewModelScope.launch {
            Log.d("OkHome", "refreshTaskCompleted")
            val list = mutableListOf<Answer>()
            try {
                if (_currentQuestionPage == 1) {
                    val moreAnswers = homeRepository.getAnswers(1).answers.toMutableList()
                    moreAnswers.reverse()
                    list.addAll(0, moreAnswers)
                } else {
                    Log.d("OkHome", "$_currentQuestionPage")
                    for (i in 1.._currentQuestionPage) {
                        val moreAnswers = homeRepository.getAnswers(i).answers.toMutableList()
                        moreAnswers.reverse()
                        list.addAll(0, moreAnswers)
                    }
                }
                _answerList.value = mutableListOf()
                _answerList.value = list.toMutableList()
                Log.d("OkHome", "refreshTaskCompletedSuccess")
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    _currentQuestionPage--
                    Log.d("OkHome", "$_currentQuestionPage")
                    canAdd = false
                    Log.d("OkHome", "refreshTaskCompletedFailure")
                } else {
                    _currentQuestionPage--
                    _errorMessage.value = "서버 통신에 문제가 발생했습니다"
                }
            }
        }
    }

    fun refreshList(list: MutableList<Answer>) {
        _answerList.value = list
    }

    fun changePublic(position: Int) {
        viewModelScope.launch {
            try {
                val currentList = _answerList.value!!.toMutableList()
                val response = homeRepository.modifyPublic(
                    currentList[position].id,
                    transitPublicFlag(currentList[position].publicFlag)
                )
                if (response.success) {
                    currentList[position].publicFlag =
                        transitPublicFlag(currentList[position].publicFlag)
                    _answerList.value = currentList
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun changeQuestion(position: Int) {
        viewModelScope.launch {
            try {
                val currentList = _answerList.value!!.toMutableList()
                val response = homeRepository.changeQuestion(currentList[position - 1].id)
                if (response.success) {
                    currentList[position - 1] = response.answer
                    _answerList.value = mutableListOf()
                    _answerList.value = currentList
                    startEvent()
                }
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    _errorMessage.value = "새로운 질문이 없습니다"
                } else {
                    _errorMessage.value = "서버 통신 중 오류가 발생했습니다"
                }
            }
        }
    }

    fun deleteAnswer(position: Int) {
        viewModelScope.launch {
            val currentList = _answerList.value ?: mutableListOf()
            try {
                val response = homeRepository.deleteAnswer(currentList[position].id)
                if (response.success) {
                    currentList.removeAt(position)
                    _answerList.value = currentList
                    startEvent()
                }
            } catch (e: HttpException) {
            }
        }
    }

    private fun transitPublicFlag(publicFlag: Int): Int {
        return when (publicFlag) {
            0 -> 1
            else -> 0
        }
    }

    fun getMoreQuestion() {
        viewModelScope.launch {
            val currentList = _answerList.value ?: mutableListOf()
            try {
                val moreQuestion = homeRepository.getNewAnswer()
                currentList.add(moreQuestion.answer)
                _answerList.value = mutableListOf()
                _answerList.value = currentList
                _successMessage.value = "질문이 추가되었습니다"
            } catch (e: HttpException) {
                _errorMessage.value = "새로운 질문이 없습니다"
            }
        }
    }

    private fun startEvent() {
        _returnToStartEvent.value = true
    }

    fun setReadyToReceiveEvent() {
        _returnToStartEvent.value = false
    }
}
