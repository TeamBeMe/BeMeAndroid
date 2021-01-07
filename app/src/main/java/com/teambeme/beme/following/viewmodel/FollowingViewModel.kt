package com.teambeme.beme.following.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.OtherQuestionsData

class FollowingViewModel : ViewModel() {
    private val _otherQuestionsFromFollowingList = MutableLiveData<MutableList<OtherQuestionsData>>()
    val otherQuestionsFromFollowingList: LiveData<MutableList<OtherQuestionsData>>
        get() = _otherQuestionsFromFollowingList


}