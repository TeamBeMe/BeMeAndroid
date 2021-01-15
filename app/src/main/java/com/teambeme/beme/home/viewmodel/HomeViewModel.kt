package com.teambeme.beme.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.home.model.Answer
import com.teambeme.beme.home.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _answerList = MutableLiveData<MutableList<Answer>>()
    val answerList: LiveData<MutableList<Answer>>
        get() = _answerList
    private var _currentQuestionPage = 0
    private var canAdd = true

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _returnToStartEvent = MutableLiveData<Boolean>()
    val returnToStartEvent: LiveData<Boolean>
        get() = _returnToStartEvent

    fun getMoreAnswers() {
        viewModelScope.launch {
            try {
                if (canAdd) {
                    val moreAnswers =
                        homeRepository.getAnswers(_currentQuestionPage++).answers.toMutableList()
                    moreAnswers.reverse()
                    val currentList = _answerList.value?.toMutableList()
                    if (currentList != null) {
                        moreAnswers.addAll(currentList)
                    }
                    _answerList.value = moreAnswers.toMutableList()
                } else {
                    val moreAnswers =
                        homeRepository.getAnswers(_currentQuestionPage).answers.toMutableList()
                    moreAnswers.reverse()
                    val currentList = _answerList.value?.toMutableList()
                    if (currentList != null) {
                        moreAnswers.addAll(currentList)
                    }
                    _answerList.value = moreAnswers.toMutableList()
                    canAdd = true
                }
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    _errorMessage.value = "더 이상 페이지가 없습니다"
                    canAdd = false
                } else {
                    _errorMessage.value = "서버 통신에 문제가 발생했습니다"
                }
            }
        }
    }

    fun refreshTaskCompleted() {
        viewModelScope.launch {
            val list = mutableListOf<Answer>()
            for (i in 1.._currentQuestionPage) {
                val moreAnswers = homeRepository.getAnswers(i).answers.toMutableList()
                moreAnswers.reverse()
                list.addAll(0, moreAnswers)
            }
            _answerList.value = list.toMutableList()
        }
    }

    fun refreshList(list: MutableList<Answer>) {
        _answerList.value = list
    }

    fun setInitAnswer() {
        viewModelScope.launch {
            try {
                val currentList =
                    homeRepository.getAnswers(_currentQuestionPage++).answers.toMutableList()
                _answerList.value = currentList.reversed().toMutableList()
                startEvent()
                delay(1000)
            } catch (e: HttpException) {
                Log.d("Home", e.message())
            }
        }
    }

    fun changePublic(position: Int) {
        viewModelScope.launch {
            try {
                val currentList = _answerList.value!!
                val response = homeRepository.modifyPublic(
                    currentList[position].id,
                    currentList[position].publicFlag
                )
                if (response.success) {
                    currentList[position].publicFlag = isPublic(currentList[position].publicFlag)
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
                val response = homeRepository.changeQuestion(currentList[position].id)
                if (response.success) {
                    currentList[position] = response.answer
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

    private fun isPublic(publicFlag: Int): Int {
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
                _answerList.value = currentList
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