package com.teambeme.beme.explore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.OthermindsData

class ExploreViewModel : ViewModel() {
    private val _othermindsList = MutableLiveData<List<OthermindsData>>()
    val othermindsList: LiveData<List<OthermindsData>>
        get() = _othermindsList

    fun setDummyOtherminds() {
        val dummyOthermindsList = listOf(
            OthermindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            ),
            OthermindsData(
                title = "나는 요즘 무엇을 사랑하고 잇나요?",
                content = "비미비미비미업, 비미비미비미업, 비미비미비미업"
            ),
            OthermindsData(
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이"
            ),
            OthermindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            ),
            OthermindsData(
                title = "나는 요즘 무엇을 사랑하고 잇나요?",
                content = "비미비미비미업, 비미비미비미업, 비미비미비미업"
            ),
            OthermindsData(
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이"
            ),
            OthermindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            )
        )
        _othermindsList.value = dummyOthermindsList.toList()
    }
}