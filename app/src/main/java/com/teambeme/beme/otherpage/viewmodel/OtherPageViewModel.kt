package com.teambeme.beme.otherpage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.otherpage.model.Answer

class OtherPageViewModel : ViewModel() {
    private val _otherAnswerList = MutableLiveData<MutableList<Answer>>()
    val otherAnswerList: LiveData<MutableList<Answer>>
        get() = _otherAnswerList

    private val dummyOtherAnswerList = mutableListOf(
        Answer(
            id = 4,
            commentBlockedFlag = false,
            content = "aa",
            answerDate = "1. 4",
            answerIdx = 1,
            questionId = 3,
            userId = 1,
            isAuthor = false,
            isScrapped = true,
            userProfile = null,
            userNickname = "wlgus3",
            question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
            category = "이야기",
            categoryId = 3,
            isAnswered = false,
            publicFlag = true
        ),
        Answer(
            id = 4,
            commentBlockedFlag = false,
            content = "aa",
            answerDate = "1. 4",
            answerIdx = 1,
            questionId = 3,
            userId = 1,
            isAuthor = false,
            isScrapped = true,
            userProfile = null,
            userNickname = "wlgus3",
            question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
            category = "이야기",
            categoryId = 3,
            isAnswered = false,
            publicFlag = true
        ),
        Answer(
            id = 4,
            commentBlockedFlag = false,
            content = "aa",
            answerDate = "1. 4",
            answerIdx = 1,
            questionId = 3,
            userId = 1,
            isAuthor = false,
            isScrapped = true,
            userProfile = null,
            userNickname = "wlgus3",
            question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
            category = "이야기",
            categoryId = 3,
            isAnswered = false,
            publicFlag = true
        ),
        Answer(
            id = 4,
            commentBlockedFlag = false,
            content = "aa",
            answerDate = "1. 4",
            answerIdx = 1,
            questionId = 3,
            userId = 1,
            isAuthor = false,
            isScrapped = true,
            userProfile = null,
            userNickname = "wlgus3",
            question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
            category = "이야기",
            categoryId = 3,
            isAnswered = false,
            publicFlag = true
        ),
        Answer(
            id = 4,
            commentBlockedFlag = false,
            content = "aa",
            answerDate = "1. 4",
            answerIdx = 1,
            questionId = 3,
            userId = 1,
            isAuthor = false,
            isScrapped = true,
            userProfile = null,
            userNickname = "wlgus3",
            question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
            category = "이야기",
            categoryId = 3,
            isAnswered = false,
            publicFlag = true
        )

    )

    fun setDummyOtherAnswer() {
        _otherAnswerList.value = dummyOtherAnswerList.toMutableList()
    }

    fun addDummyAnswer() {
        val dummyAnswer = listOf(
            Answer(
                id = 4,
                commentBlockedFlag = false,
                content = "aa",
                answerDate = "1. 4",
                answerIdx = 1,
                questionId = 3,
                userId = 1,
                isAuthor = false,
                isScrapped = true,
                userProfile = null,
                userNickname = "wlgus3",
                question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
                category = "이야기",
                categoryId = 3,
                isAnswered = false,
                publicFlag = true
            ),
            Answer(
                id = 4,
                commentBlockedFlag = false,
                content = "aa",
                answerDate = "1. 4",
                answerIdx = 1,
                questionId = 3,
                userId = 1,
                isAuthor = false,
                isScrapped = true,
                userProfile = null,
                userNickname = "wlgus3",
                question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
                category = "이야기",
                categoryId = 3,
                isAnswered = false,
                publicFlag = true
            ),
            Answer(
                id = 4,
                commentBlockedFlag = false,
                content = "aa",
                answerDate = "1. 4",
                answerIdx = 1,
                questionId = 3,
                userId = 1,
                isAuthor = false,
                isScrapped = true,
                userProfile = null,
                userNickname = "wlgus3",
                question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
                category = "이야기",
                categoryId = 3,
                isAnswered = false,
                publicFlag = true
            ),
            Answer(
                id = 4,
                commentBlockedFlag = false,
                content = "aa",
                answerDate = "1. 4",
                answerIdx = 1,
                questionId = 3,
                userId = 1,
                isAuthor = false,
                isScrapped = true,
                userProfile = null,
                userNickname = "wlgus3",
                question = "두려움을 극복해본 경험이 있나요? 어떤 상황이었나요?",
                category = "이야기",
                categoryId = 3,
                isAnswered = false,
                publicFlag = true
            )
        )
        _otherAnswerList.value?.addAll(dummyAnswer.toMutableList())
    }
}