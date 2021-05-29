package com.teambeme.beme.answer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.data.local.entity.AnswerData
import com.teambeme.beme.data.repository.AnswerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class AnswerViewModel @Inject constructor(
    private val answerRepository: AnswerRepository
) : ViewModel() {
    private val _answerData = MutableLiveData<AnswerData?>()
    val answerData: LiveData<AnswerData?>
        get() = _answerData
    val answer: MutableLiveData<String> = MutableLiveData("")
    private var _isCommentBlocked = false
    val isCommentBlocked: Boolean
        get() = _isCommentBlocked
    private val _isPublic = MutableLiveData<Boolean>()
    val isPublic: LiveData<Boolean>
        get() = _isPublic
    private val _intentAnswerData = MutableLiveData<IntentAnswerData>()
    val intentAnswerData: LiveData<IntentAnswerData>
        get() = _intentAnswerData

    fun checkStored(questionId: Int) {
        viewModelScope.launch {
            val data = answerRepository.get(questionId.toLong())
            Log.d("AnswerViewModel", data.toString())
            _answerData.value = data
            _isPublic.value = data?.isPublic ?: true
        }
    }

    fun registerAnswer(requestAnswerData: RequestAnswerData) {
        viewModelScope.launch {
            try {
                answerRepository.registerAnswer(requestAnswerData)
            } catch (e: HttpException) {
            }
        }
    }

    fun modifyAnswer(requestAnswerData: RequestAnswerData) {
        viewModelScope.launch {
            try {
                answerRepository.modifyAnswer(requestAnswerData)
            } catch (e: HttpException) {
            }
        }
    }

    fun initAnswerData(intentAnswerData: IntentAnswerData) {
        viewModelScope.launch {
            val answerData = AnswerData(
                questionId = intentAnswerData.questionId.toLong(),
                answerId = intentAnswerData.answerId.toLong(),
                answer = intentAnswerData.content,
                isCommentBlocked = intentAnswerData.isCommentBlocked,
                isPublic = intentAnswerData.isPublic,
                title = intentAnswerData.title,
                category = intentAnswerData.category,
                categoryIdx = intentAnswerData.categoryIdx ?: 0,
                createdAt = intentAnswerData.createdAt
            )
            _answerData.value = answerData
            _isPublic.value = intentAnswerData.isPublic
            answerRepository.insert(answerData)
        }
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

    fun setIntentAnswerData(intentAnswerData: IntentAnswerData) {
        _intentAnswerData.value = intentAnswerData
    }

    fun storeAnswer() {
        viewModelScope.launch {
            Log.d("AnswerViewModel", answerData.value.toString())
            answerRepository.insert(
                AnswerData(
                    questionId = answerData.value!!.questionId,
                    answerId = answerData.value!!.answerId,
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
