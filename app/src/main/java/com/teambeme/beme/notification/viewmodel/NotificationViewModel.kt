package com.teambeme.beme.notification.viewmodel

import android.app.Notification
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.notification.model.RecentActivitiesData

class NotificationViewModel : ViewModel() {
    private val _notificationData = MutableLiveData<MutableList<RecentActivitiesData>>()
    val notificationData: LiveData<MutableList<RecentActivitiesData>>
        get() = _notificationData


        private val dummyNotiList = mutableListOf(
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.1"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.2"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.3"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.4"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.5"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.6"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.7"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.8"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.9"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.10")
        )
    fun setDummyNoti() {
        _notificationData.value = dummyNotiList.toMutableList()
    }

    fun addDummyNoti() {
        //리스트들을 애드 더미 노티는 못하나?
        val plusdummyNoti = listOf(
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.11"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.12"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.13"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.14"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.15"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.16"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.17"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.18"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.19"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.20")
        )
        dummyNotiList.addAll(plusdummyNoti.toMutableList())
        _notificationData.value = dummyNotiList.toMutableList()
    }
}

