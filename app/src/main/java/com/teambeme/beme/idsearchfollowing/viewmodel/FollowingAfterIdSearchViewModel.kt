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
        RecentSearchData("aaa1", ""),
        RecentSearchData("aaa2", ""),
        RecentSearchData("aaa3", ""),
        RecentSearchData("aaa4", ""),
        RecentSearchData("aaa5", ""),
        RecentSearchData("aaa6", ""),
        RecentSearchData("aaa7", ""),
        RecentSearchData("aaa8", ""),
        RecentSearchData("aaa9", ""),
        RecentSearchData("aaa10", ""),
        RecentSearchData("aaa11", ""),
        RecentSearchData("aaa12", ""),
        RecentSearchData("aaa13", ""),
        RecentSearchData("aaa14", ""),
        RecentSearchData("aaa15", ""),
        RecentSearchData("aaa16", ""),
        RecentSearchData("aaa17", ""),
        RecentSearchData("aaa18", ""),
        RecentSearchData("aaa19", ""),
        RecentSearchData("aaa20", ""),
        RecentSearchData("aaa21", "")
    )

    fun setDummyRecentSearch() {
        _recentSearchList.value = dummyRecentSearchList.toMutableList()
    }

    private val _idSearchList = MutableLiveData<MutableList<IdSearchData>>()
    val idSearchList: LiveData<MutableList<IdSearchData>>
        get() = _idSearchList

    private val dummyIdSearchList = mutableListOf(
        IdSearchData("1_ox", ""),
        IdSearchData("2_xo", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", ""),
        IdSearchData("aaa", "")
    )

    fun setDummyIdSearchList() {
        _idSearchList.value = dummyIdSearchList.toMutableList()
    }
}