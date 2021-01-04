package com.teambeme.beme.answer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.data.local.dao.AnswerDao

class AnswerViewModelFactory(
    private val dataSource: AnswerDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnswerViewModel::class.java)) {
            return AnswerViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}