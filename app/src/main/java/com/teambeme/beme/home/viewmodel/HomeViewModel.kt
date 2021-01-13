package com.teambeme.beme.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.home.model.Answer
import com.teambeme.beme.home.repository.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _answerList = MutableLiveData<MutableList<Answer>>()
    val answerList: LiveData<MutableList<Answer>>
        get() = _answerList
    private var currentQuestionPage = 1

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _returnToStartEvent = MutableLiveData<Boolean>()
    val returnToStartEvent: LiveData<Boolean>
        get() = _returnToStartEvent

    fun getAnswers() {
        val answerList = mutableListOf<Answer>()
        answerList.addAll(_answerList.value ?: mutableListOf())

    }

    fun setInitAnswer() {
        viewModelScope.launch {
            try {
                _answerList.value =
                    homeRepository.getAnswers(currentQuestionPage++).answers.toMutableList()
                startEvent()
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
                val errorMessage = e.message()
                _errorMessage.value = errorMessage
            }
        }
    }

    fun startEvent() {
        _returnToStartEvent.value = true
    }

    fun setReadyToReceiveEvent() {
        _returnToStartEvent.value = false
    }
}