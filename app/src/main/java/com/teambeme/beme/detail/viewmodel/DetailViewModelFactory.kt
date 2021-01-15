package com.teambeme.beme.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.detail.repository.DetailRepository

class DetailViewModelFactory(private val detailRepository: DetailRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(detailRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}