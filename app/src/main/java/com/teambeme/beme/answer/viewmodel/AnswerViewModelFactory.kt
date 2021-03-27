package com.teambeme.beme.answer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.data.repository.AnswerRepository

class AnswerViewModelFactory(
    private val answerRepository: AnswerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnswerViewModel::class.java)) {
            return AnswerViewModel(answerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}