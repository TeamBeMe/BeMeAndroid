package com.teambeme.beme.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.home.repository.HomeRepository
import com.teambeme.beme.login.viewmodel.LoginViewModel

class HomeViewModelFactory(private val homeRepository: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}