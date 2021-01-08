package com.teambeme.beme.answer.viewmodel

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
    var answer: String = ""
    private var questionId = -1

    suspend fun initEditText(id: Int): String {
        return viewModelScope.async {
            answer = getStoredAnswer(id.toLong())?.answer ?: ""
            questionId = id
            answer
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
                    answer = answer
                )
            )
        }
    }
}