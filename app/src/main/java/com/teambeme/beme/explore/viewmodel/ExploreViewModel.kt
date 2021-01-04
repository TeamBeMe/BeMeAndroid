package com.teambeme.beme.explore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.OthermindsData
import com.teambeme.beme.explore.model.OtherquestionsData

class ExploreViewModel : ViewModel() {
    private val _othermindsList = MutableLiveData<List<OthermindsData>>()
    val othermindsList: LiveData<List<OthermindsData>>
        get() = _othermindsList

    private val _otherquestionsList = MutableLiveData<MutableList<OtherquestionsData>>()
    val otherquestionsList: LiveData<MutableList<OtherquestionsData>>
        get() = _otherquestionsList

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

    fun setDummyOtherquestions() {
        val dummyOtherquestionsList = listOf(
            OtherquestionsData(
                userId = "1",
                category = "가치관",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "5"
            ),
            OtherquestionsData(
                userId = "2",
                category = "사랑",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "26"
            ),
            OtherquestionsData(
                userId = "3",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15"
            ),
            OtherquestionsData(
                userId = "4",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3"
            ),
            OtherquestionsData(
                userId = "5",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4"
            ),
            OtherquestionsData(
                userId = "6",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5"
            ),
            OtherquestionsData(
                userId = "7",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15"
            ),
            OtherquestionsData(
                userId = "8",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3"
            ),
            OtherquestionsData(
                userId = "9",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4"
            ),
            OtherquestionsData(
                userId = "10",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5"
            )
        )
        _otherquestionsList.value = dummyOtherquestionsList.toMutableList()
    }
}