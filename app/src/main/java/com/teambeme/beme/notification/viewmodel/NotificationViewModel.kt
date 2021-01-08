package com.teambeme.beme.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.notification.model.RecentActivitiesData

class NotificationViewModel : ViewModel() {
    private val _recentActivitiesList = MutableLiveData<MutableList<RecentActivitiesData>>()
    val recentActivitiesList: LiveData<MutableList<RecentActivitiesData>>
        get() = _recentActivitiesList

    private val dummyNotiRecentList = mutableListOf(
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.1"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.2"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.3"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.4"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.5"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.6"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.7"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.8"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.9"),
        RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.10")
    )

    fun setDummyRecentNotification() {
        _recentActivitiesList.value = dummyNotiRecentList.toMutableList()
    }

    fun addDummyRecentNotification() {
        val plusdummyNotiRecentList = listOf(
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.11"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.12"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.13"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.14"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.15"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.16"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.17"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.18"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.19"),
            RecentActivitiesData("aaa", "", "aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.20")
        )
        dummyNotiRecentList.addAll(plusdummyNotiRecentList.toMutableList())
        _recentActivitiesList.value = dummyNotiRecentList.toMutableList()
    }
}