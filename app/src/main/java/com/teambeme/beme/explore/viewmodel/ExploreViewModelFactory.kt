package com.teambeme.beme.explore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.explore.repository.ExploreRepository
import java.lang.IllegalArgumentException

class ExploreViewModelFactory(private val exploreRepository: ExploreRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExploreViewModel::class.java)){
            return ExploreViewModel(exploreRepository) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}