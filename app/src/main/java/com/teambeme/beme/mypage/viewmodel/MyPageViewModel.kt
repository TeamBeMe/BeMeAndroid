package com.teambeme.beme.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.mypage.model.MyScrap
import com.teambeme.beme.mypage.model.MyWrite

class MyPageViewModel:ViewModel(){
    private val _mypageWriteData=MutableLiveData<MutableList<MyWrite>>()
    val mypageWriteData: LiveData<MutableList<MyWrite>>
        get()=_mypageWriteData

    fun setDummyWrite(){
        val dummyWrite=listOf(
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "으음",
                time="5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "행복",
                time="5분 전",
                isSecret = true
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "사랑",
                time="5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "미래",
                time="5분 전",
                isSecret = false
            )
        )
        _mypageWriteData.value=dummyWrite.toMutableList()
    }

    private val _mypageScrapData=MutableLiveData<MutableList<MyScrap>>()
    val mypageScrapData: LiveData<MutableList<MyScrap>>
        get()=_mypageScrapData

    fun setDummyScrap(){
        val dummyScrap=listOf(
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                userId = "iron man"
            )
        )
        _mypageScrapData.value=dummyScrap.toMutableList()
    }
}