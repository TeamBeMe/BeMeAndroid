package com.teambeme.beme.explore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.OtherMindsData
import com.teambeme.beme.explore.model.OtherQuestionsData

class ExploreViewModel : ViewModel() {
    private val _otherMindsList = MutableLiveData<List<OtherMindsData>>()
    val otherMindsList: LiveData<List<OtherMindsData>>
        get() = _otherMindsList

    private val _otherQuestionsList = MutableLiveData<MutableList<OtherQuestionsData>>()
    val otherQuestionsList: LiveData<MutableList<OtherQuestionsData>>
        get() = _otherQuestionsList

    private val _otherAnswersList = MutableLiveData<MutableList<OtherQuestionsData>>()
    val otherAnswersList: LiveData<MutableList<OtherQuestionsData>>
        get() = _otherAnswersList

    private val dummyOtherAnswersList = mutableListOf(
        OtherQuestionsData(
            userId = "1",
            category = "가치관",
            title = null,
            content = "답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.답변1입니다.",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "2",
            category = "사랑",
            title = null,
            content = "답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.답변2입니다.",
            time = "26",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "3",
            category = "일상",
            title = null,
            content = "답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.답변3입니다.",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "4",
            category = "이야기",
            title = null,
            content = "답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.답변4입니다.",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "5",
            category = "미래",
            title = null,
            content = "답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.답변5입니다.",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "6",
            category = "의미",
            title = null,
            content = "답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.답변6입니다.",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "7",
            category = "일상",
            title = null,
            content = "답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.답변7입니다.",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "8",
            category = "이야기",
            title = null,
            content = "답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.답변8입니다.",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "9",
            category = "미래",
            title = null,
            content = "답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.답변9입니다.",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "10",
            category = "의미",
            title = null,
            content = "답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.답변10입니다.",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        )
    )

    fun setDummyOtherAnswers() {
        _otherAnswersList.value = dummyOtherAnswersList.toMutableList()
    }

    fun plusDummyOtherAnswers() {
        val plusOtherAnswersList = listOf(
            OtherQuestionsData(
                userId = "11",
                category = "가치관",
                title = null,
                content = "답변11입니다.",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "12",
                category = "사랑",
                title = null,
                content = "답변12입니다.",
                time = "26",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "13",
                category = "일상",
                title = null,
                content = "답변13입니다.",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "14",
                category = "이야기",
                title = null,
                content = "답변14입니다.",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "15",
                category = "미래",
                title = null,
                content = "답변15입니다.",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "16",
                category = "의미",
                title = null,
                content = "답변16입니다.",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "17",
                category = "일상",
                title = null,
                content = "답변17입니다.",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "18",
                category = "이야기",
                title = null,
                content = "답변18입니다.",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "19",
                category = "미래",
                title = null,
                content = "답변19입니다.",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "20",
                category = "의미",
                title = null,
                content = "답변20입니다.",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            )
        )
        dummyOtherAnswersList.addAll(plusOtherAnswersList.toMutableList())
        _otherAnswersList.value = dummyOtherAnswersList.toMutableList()
    }

    private val dummyOtherQuestionsList = mutableListOf(
        OtherQuestionsData(
            userId = "1",
            category = "가치관",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "2",
            category = "사랑",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "26",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "3",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "4",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "5",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "6",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "7",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "8",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "9",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "10",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        )
    )

    fun setDummyOtherQuestions() {
        _otherQuestionsList.value = dummyOtherQuestionsList.toMutableList()
    }

    fun plusDummyOtherQuestions() {
        val plusOtherQuestionsList = listOf(
            OtherQuestionsData(
                userId = "11",
                category = "가치관",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "12",
                category = "사랑",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "26",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "13",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "14",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "15",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "16",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "17",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "18",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "19",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "20",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            )
        )
        dummyOtherQuestionsList.addAll(plusOtherQuestionsList.toMutableList())
        _otherQuestionsList.value = dummyOtherQuestionsList.toMutableList()
    }

    fun setDummyOtherMinds() {
        val dummyOtherMindsList = listOf(
            OtherMindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            ),
            OtherMindsData(
                title = "나는 요즘 무엇을 사랑하고 잇나요?",
                content = "비미비미비미업, 비미비미비미업, 비미비미비미업"
            ),
            OtherMindsData(
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이"
            ),
            OtherMindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            ),
            OtherMindsData(
                title = "나는 요즘 무엇을 사랑하고 잇나요?",
                content = "비미비미비미업, 비미비미비미업, 비미비미비미업"
            ),
            OtherMindsData(
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이"
            ),
            OtherMindsData(
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재"
            )
        )
        _otherMindsList.value = dummyOtherMindsList.toList()
    }
}