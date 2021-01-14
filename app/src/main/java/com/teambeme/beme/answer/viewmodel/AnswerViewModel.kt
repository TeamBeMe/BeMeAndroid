package com.teambeme.beme.answer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.repository.AnswerRepository
import com.teambeme.beme.data.local.entity.AnswerData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AnswerViewModel(private val answerRepository: AnswerRepository) : ViewModel() {
    private val _answerData = MutableLiveData<AnswerData?>()
    val answerData: LiveData<AnswerData?>
        get() = _answerData
    val answer: MutableLiveData<String> = MutableLiveData("")
    private var _isCommentBlocked = false
    val isCommentBlocked: Boolean
        get() = _isCommentBlocked
    private val _isPublic = MutableLiveData<Boolean>(false)
    val isPublic: LiveData<Boolean>
        get() = _isPublic

    fun checkStored(questionId: Int) {
        viewModelScope.launch {
            val data = answerRepository.get(questionId.toLong())
            Log.d("AnswerViewModel", data.toString())
            _answerData.value = data
        }
    }

    fun registerAnswer(requestAnswerData: RequestAnswerData) {
        viewModelScope.launch {
            try {
                answerRepository.registerAnswer(requestAnswerData)
            } catch (e: HttpException) { }
        }
    }

    fun modifyAnswer(requestAnswerData: RequestAnswerData) {
        viewModelScope.launch {
            try {
                answerRepository.modifyAnswer(requestAnswerData)
            } catch (e: HttpException) { }
        }
    }

    fun initAnswerData(intentAnswerData: IntentAnswerData) {
        _answerData.value = AnswerData(
            questionId = intentAnswerData.questionId.toLong(),
            answer = "",
            isCommentBlocked = _isCommentBlocked,
            isPublic = false,
            title = intentAnswerData.title,
            category = intentAnswerData.category,
            categoryIdx = intentAnswerData.categoryIdx ?: 0,
            createdAt = intentAnswerData.createdAt
        )
    }

    fun setPublicStatus(boolean: Boolean) {
        _isPublic.value = boolean
    }

    fun setCommentBlockedStatus(boolean: Boolean) {
        _isCommentBlocked = boolean
    }

    fun initEditText() {
        answer.value = answerData.value?.answer.toString()
    }

    fun storeAnswer() {
        viewModelScope.launch {
            Log.d("AnswerViewModel", answerData.value.toString())
            answerRepository.insert(
                AnswerData(
                    questionId = answerData.value!!.questionId,
                    answer = answer.value!!,
                    isCommentBlocked = _isCommentBlocked,
                    isPublic = isPublic.value ?: true,
                    title = answerData.value!!.title,
                    category = answerData.value!!.category,
                    categoryIdx = answerData.value!!.categoryIdx,
                    createdAt = answerData.value!!.createdAt
                )
            )
        }
    }
}