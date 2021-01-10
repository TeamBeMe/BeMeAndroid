package com.teambeme.beme.otherpage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.otherpage.model.ResponseOtherData
import com.teambeme.beme.otherpage.model.ResponseOtherData.Data.Answer
import com.teambeme.beme.otherpage.repository.OtherPageRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherPageViewModel(private val otherRepository: OtherPageRepository) : ViewModel() {
    private val _otherAnswerList = MutableLiveData<MutableList<Answer>>()
    val otherAnswerList: LiveData<MutableList<Answer>>
        get() = _otherAnswerList

    fun requestItem() {
        otherRepository.getProfileAnswer(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjU0NDA2LCJleHAiOjE2MTAyNzk2MDYsImlzcyI6ImJlbWUifQ.PRJdPf_GoDaQuIE6WNb0UbsiZJUoVG9gS16Q2Mb1aeI",
            5,
            1
        ).enqueue(object :
            Callback<ResponseOtherData> {
            override fun onResponse(
                call: Call<ResponseOtherData>,
                response: Response<ResponseOtherData>
            ) {
                if (response.isSuccessful)
                    _otherAnswerList.value = response.body()!!.data?.answers?.toMutableList()
            }

            override fun onFailure(call: Call<ResponseOtherData>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
                for (element in t.stackTrace) {
                    Log.d("Network element", element.toString())
                    Log.d("Network className", element.className)
                    Log.d("Network methodName", element.methodName)
                    Log.d("Network fileName", element.fileName)
                    Log.d("Network lineNumber", element.lineNumber.toString())
                }
            }
        })
    }

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
        dummyOtherAnswerList.addAll(dummyAnswer.toMutableList())
        _otherAnswerList.value = dummyOtherAnswerList.toMutableList()
    }
}