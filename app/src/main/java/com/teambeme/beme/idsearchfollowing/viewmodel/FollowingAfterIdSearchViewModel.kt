package com.teambeme.beme.idsearchfollowing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.idsearchfollowing.model.IdSearchData
import com.teambeme.beme.idsearchfollowing.model.RecentSearchData


class FollowingAfterIdSearchViewModel : ViewModel() {
    private val _recentSearchList = MutableLiveData<MutableList<RecentSearchData>>()
    val recentSearchList: LiveData<MutableList<RecentSearchData>>
        get() = _recentSearchList

    private val dummyRecentSearchList = mutableListOf(
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", ""),
        RecentSearchData("aaa", "")
    )

    fun setDummyRecentSearch() {
        _recentSearchList.value = dummyRecentSearchList.toMutableList()
    }

    private val _idSearchList = MutableLiveData<MutableList<IdSearchData>>()
    val idSearchList: LiveData<MutableList<IdSearchData>>
        get() = _idSearchList

    private val dummyIdSearchList = mutableListOf(
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", "")

    )

    fun setDummyIdSearchList() {
        _idSearchList.value = dummyIdSearchList.toMutableList()
    }

}