package com.teambeme.beme.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.data.repository.MyPageRepository

class MyPageViewModelFactory(private val myRepository: MyPageRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            return MyPageViewModel(myRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}