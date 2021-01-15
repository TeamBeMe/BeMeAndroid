package com.teambeme.beme.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.notification.repository.NoticeRepository

class NoticeViewModelFactory(private val noticeRepository: NoticeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoticeViewModel ::class.java)) {
            return NoticeViewModel(noticeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
