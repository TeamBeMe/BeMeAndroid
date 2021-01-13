package com.teambeme.beme.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.home.model.Answer
import com.teambeme.beme.home.model.ResponseQuestionData
import com.teambeme.beme.home.repository.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _questionList = MutableLiveData<MutableList<ResponseQuestionData.Answer>>()
    val questionList: LiveData<MutableList<ResponseQuestionData.Answer>>
        get() = _questionList

    private val _answerList = MutableLiveData<MutableList<Answer>>()
    val answerList: LiveData<MutableList<Answer>>
        get() = _answerList
    private var currentQuestionPage = 1

    fun getAnswers() {
        val answerList = mutableListOf<Answer>()
        answerList.addAll(_answerList.value ?: mutableListOf())

    }

    fun setInitAnswer() {
        viewModelScope.launch {
            try {
                _answerList.value = homeRepository.getAnswers(currentQuestionPage++).answers.toMutableList()
            } catch (e: HttpException) {
                Log.d("Home", e.message())
            }
        }
    }
}