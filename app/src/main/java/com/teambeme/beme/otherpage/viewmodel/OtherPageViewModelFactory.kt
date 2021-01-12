package com.teambeme.beme.otherpage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.otherpage.repository.OtherPageRepository

class OtherPageViewModelFactory(private val otherRepository: OtherPageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OtherPageViewModel::class.java)) {
            return OtherPageViewModel(otherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}