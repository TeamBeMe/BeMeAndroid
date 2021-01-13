package com.teambeme.beme.following.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.following.repository.FollowingRepository

class FollowingViewModelFactory(private val followingRepository: FollowingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowingViewModel::class.java)) {
            return FollowingViewModel(followingRepository) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}