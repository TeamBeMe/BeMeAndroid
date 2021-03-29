package com.teambeme.beme.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.util.SingleLiveEvent

class EventViewModel : ViewModel() {
    private val _firstButtonClicked = SingleLiveEvent<Unit>()
    val firstButtonClicked: LiveData<Unit>
        get() = _firstButtonClicked
    private val _secondButtonClicked = SingleLiveEvent<Unit>()
    val secondButtonClicked: LiveData<Unit>
        get() = _secondButtonClicked
    private val _thirdButtonClicked = SingleLiveEvent<Unit>()
    val thirdButtonClicked: LiveData<Unit>
        get() = _thirdButtonClicked
    private val _fourthButtonClicked = SingleLiveEvent<Unit>()
    val fourthButtonClicked: LiveData<Unit>
        get() = _fourthButtonClicked

    fun buttonClickedAt(position: Int) {
        when (position) {
            0 -> _firstButtonClicked.call()
            1 -> _secondButtonClicked.call()
            2 -> _thirdButtonClicked.call()
            else -> _fourthButtonClicked.call()
        }
    }
}