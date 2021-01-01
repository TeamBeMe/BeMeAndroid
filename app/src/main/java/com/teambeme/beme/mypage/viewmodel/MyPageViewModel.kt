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
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
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
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time="5분 전",
                content = "저는 며칠 전 퇴사를 했어요. 수많은 고민 끝에 결국 저질렀습니다. " +
                        "몇 년간 원해왔던 일이라 꿈만 같아요. 제가 스스로의 힘으로 하고 싶은 걸 " +
                        "해볼 수 있는 시간적 여유를 가지게 된 게 정말 만족스러워요. " +
                        "앞으로 저에게는 정말 다양한 가능성들이 있으니...",
                userId = "iron man"
            )
        )
        _mypageScrapData.value=dummyScrap.toMutableList()
    }
}