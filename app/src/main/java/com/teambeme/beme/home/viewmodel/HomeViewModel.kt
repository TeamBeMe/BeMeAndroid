package com.teambeme.beme.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.home.model.Question

class HomeViewModel : ViewModel() {
    private val _questionList = MutableLiveData<MutableList<Question>>()
    val questionList: LiveData<MutableList<Question>>
        get() = _questionList

    fun setDummyQuestions() {
        val dummyQuestionList = listOf(
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            ),
            Question(
                isLock = true,
                info = "[ 미래에 관한 2번째 질문 ]",
                date = "2020.12.24",
                title = "이번 주말을 후회 없이\n보내는 방법은 무엇인가요?",
                content = "저는 몇일 전 퇴사를 했어요. 그래서 웅앵웅웅앵 수많은 고민 끝에 저질렀답니다. 엄청난 실수이었지만 저는 해낼 수가 있었고 없었고 웅웅웅"
            )
        )
        _questionList.value = dummyQuestionList.toMutableList()
    }
}