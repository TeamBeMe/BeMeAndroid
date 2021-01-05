package com.teambeme.beme.explore.viewmodel

import android.util.Log
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

    private val _otheranswersList = MutableLiveData<MutableList<OtherquestionsData>>()
    val otheranswersList: LiveData<MutableList<OtherquestionsData>>
        get() = _otheranswersList

    private val dummyOtheranswersList = mutableListOf(
        OtherquestionsData(
            userId = "1",
            category = "가치관",
            title = null,
            content = "답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.",
            time = "5"
        ),
        OtherquestionsData(
            userId = "2",
            category = "사랑",
            title = null,
            content = "답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.",
            time = "26"
        ),
        OtherquestionsData(
            userId = "3",
            category = "일상",
            title = null,
            content = "답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.",
            time = "15"
        ),
        OtherquestionsData(
            userId = "4",
            category = "이야기",
            title = null,
            content = "답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.",
            time = "3"
        ),
        OtherquestionsData(
            userId = "5",
            category = "미래",
            title = null,
            content = "답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.",
            time = "4"
        ),
        OtherquestionsData(
            userId = "6",
            category = "의미",
            title = null,
            content = "답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.",
            time = "5"
        ),
        OtherquestionsData(
            userId = "7",
            category = "일상",
            title = null,
            content = "답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.",
            time = "15"
        ),
        OtherquestionsData(
            userId = "8",
            category = "이야기",
            title = null,
            content = "답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.",
            time = "3"
        ),
        OtherquestionsData(
            userId = "9",
            category = "미래",
            title = null,
            content = "답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.",
            time = "4"
        ),
        OtherquestionsData(
            userId = "10",
            category = "의미",
            title = null,
            content = "답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.",
            time = "5"
        )
    )

    fun setDummyOtheranswers() {
        _otheranswersList.value = dummyOtheranswersList.toMutableList()
    }

    fun plusDummyOtheranswers() {
        val plusOtheranswersList = listOf(
            OtherquestionsData(
                userId = "11",
                category = "가치관",
                title = null,
                content = "답변11입니다.",
                time = "5"
            ),
            OtherquestionsData(
                userId = "12",
                category = "사랑",
                title = null,
                content = "답변12입니다.",
                time = "26"
            ),
            OtherquestionsData(
                userId = "13",
                category = "일상",
                title = null,
                content = "답변13입니다.",
                time = "15"
            ),
            OtherquestionsData(
                userId = "14",
                category = "이야기",
                title = null,
                content = "답변14입니다.",
                time = "3"
            ),
            OtherquestionsData(
                userId = "15",
                category = "미래",
                title = null,
                content = "답변15입니다.",
                time = "4"
            ),
            OtherquestionsData(
                userId = "16",
                category = "의미",
                title = null,
                content = "답변16입니다.",
                time = "5"
            ),
            OtherquestionsData(
                userId = "17",
                category = "일상",
                title = null,
                content = "답변17입니다.",
                time = "15"
            ),
            OtherquestionsData(
                userId = "18",
                category = "이야기",
                title = null,
                content = "답변18입니다.",
                time = "3"
            ),
            OtherquestionsData(
                userId = "19",
                category = "미래",
                title = null,
                content = "답변19입니다.",
                time = "4"
            ),
            OtherquestionsData(
                userId = "20",
                category = "의미",
                title = null,
                content = "답변20입니다.",
                time = "5"
            )
        )
        dummyOtheranswersList.addAll(plusOtheranswersList.toMutableList())
        _otheranswersList.value = dummyOtheranswersList.toMutableList()
    }

    private val dummyOtherquestionsList = mutableListOf(
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

    fun setDummyOtherquestions() {
        _otherquestionsList.value = dummyOtherquestionsList.toMutableList()
    }

    fun plusDummyOtherquestions() {
        val plusOtherquestionsList = listOf(
            OtherquestionsData(
                userId = "11",
                category = "가치관",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "5"
            ),
            OtherquestionsData(
                userId = "12",
                category = "사랑",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "26"
            ),
            OtherquestionsData(
                userId = "13",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15"
            ),
            OtherquestionsData(
                userId = "14",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3"
            ),
            OtherquestionsData(
                userId = "15",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4"
            ),
            OtherquestionsData(
                userId = "16",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5"
            ),
            OtherquestionsData(
                userId = "17",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15"
            ),
            OtherquestionsData(
                userId = "18",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3"
            ),
            OtherquestionsData(
                userId = "19",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4"
            ),
            OtherquestionsData(
                userId = "20",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5"
            )
        )
        dummyOtherquestionsList.addAll(plusOtherquestionsList.toMutableList())
        _otherquestionsList.value = dummyOtherquestionsList.toMutableList()
    }

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