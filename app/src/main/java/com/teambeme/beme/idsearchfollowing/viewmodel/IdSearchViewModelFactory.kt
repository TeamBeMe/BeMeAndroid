package com.teambeme.beme.idsearchfollowing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.idsearchfollowing.repository.IdSearchRepository

class IdSearchViewModelFactory(private val idSearchRepository: IdSearchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdSearchViewModel ::class.java)) {
            return IdSearchViewModel(idSearchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
