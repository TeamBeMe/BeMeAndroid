package com.teambeme.beme.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.home.model.ResponseQuestionData

class HomeViewModel : ViewModel() {
    private val _questionList = MutableLiveData<MutableList<ResponseQuestionData.Answer>>()
    val questionList: LiveData<MutableList<ResponseQuestionData.Answer>>
        get() = _questionList

    fun setDummyQuestions() {
        val dummyQuestionList = listOf(
            ResponseQuestionData.Answer(
                id = 21,
                answerIdx = null,
                content = null,
                createdAt = "2021-01-03T13:33:26.000Z",
                answerDate = null,
                question = ResponseQuestionData.Answer.Question(
                    category = ResponseQuestionData.Answer.Question.Category(
                        id = 1,
                        name = "일상"
                    ),
                    id = 13,
                    title = "오늘 나를 웃게한 사람은 누구인가요?"
                ),
                isToday = true,
                publicFlag = 1
            ),
            ResponseQuestionData.Answer(
                id = 22,
                answerIdx = 2,
                content = "오늘의 나는 존나 짱짱맨이다 후우 나는 멋진 사람이야 엄청 멋지고도 멋진 사람",
                createdAt = "2021-01-03T13:33:26.000Z",
                answerDate = "2021-01-03T13:50:26.000Z",
                question = ResponseQuestionData.Answer.Question(
                    category = ResponseQuestionData.Answer.Question.Category(
                        id = 1,
                        name = "일상"
                    ),
                    id = 13,
                    title = "오늘 나를 웃게한 사람은 누구인가요?"
                ),
                isToday = false,
                publicFlag = 1
            ),
            ResponseQuestionData.Answer(
                id = 23,
                answerIdx = 3,
                content = "오늘의 나는 존나 짱짱맨이다 후우 나는 멋진 사람이야 엄청 멋지고도 멋진 사람",
                createdAt = "2021-01-03T13:33:26.000Z",
                answerDate = "2021-01-03T13:50:26.000Z",
                question = ResponseQuestionData.Answer.Question(
                    category = ResponseQuestionData.Answer.Question.Category(
                        id = 1,
                        name = "사랑앓이"
                    ),
                    id = 13,
                    title = "오늘 나를 웃게한 사람은 누구인가요?"
                ),
                isToday = false,
                publicFlag = 1
            ),
            ResponseQuestionData.Answer(
                id = 19,
                answerIdx = null,
                content = null,
                createdAt = "2021-01-05T13:33:26.000Z",
                answerDate = null,
                question = ResponseQuestionData.Answer.Question(
                    category = ResponseQuestionData.Answer.Question.Category(
                        id = 1,
                        name = "일상"
                    ),
                    id = 13,
                    title = "오늘 나를 웃게한 사람은 누구인가요?"
                ),
                isToday = true,
                publicFlag = 1
            ),
        )
        _questionList.value = dummyQuestionList.toMutableList()
    }
}