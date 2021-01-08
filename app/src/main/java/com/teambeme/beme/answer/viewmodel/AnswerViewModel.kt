package com.teambeme.beme.answer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.local.entity.AnswerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnswerViewModel(
    private val dataBase: AnswerDao
) : ViewModel() {
    private var answerId = -1
    val answer: MutableLiveData<String> = MutableLiveData("")
    private val _isCommentBlocked = MutableLiveData<Boolean>()
    val isCommentBlocked: LiveData<Boolean>
        get() = _isCommentBlocked
    private val _isPublic = MutableLiveData<Boolean>()
    val isPublic: LiveData<Boolean>
        get() = _isPublic
    private var questionId = -1

    fun setPublicTrue() {
        _isPublic.value = true
    }

    fun setPublicFalse() {
        _isPublic.value = false
    }

    suspend fun initEditText(id: Int): String {
        return viewModelScope.async {
            answer.value = getStoredAnswer(id.toLong())?.answer ?: ""
            questionId = id
            answer.value!!
        }.await()
    }

    private suspend fun getStoredAnswer(id: Long): AnswerData? {
        return withContext(Dispatchers.IO) { dataBase.get(id) }
    }

    fun storeAnswer() {
        viewModelScope.launch {
            dataBase.insert(
                AnswerData(
                    questionId = questionId.toLong(),
                    answer = answer.value!!
                )
            )
        }
    }
}