package com.teambeme.beme.mypage.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.mypage.model.MyScrap
import com.teambeme.beme.mypage.model.MyWrite
import com.teambeme.beme.mypage.model.MyWriteFilter

class MyPageViewModel : ViewModel() {
    private val _mypageWriteData = MutableLiveData<MutableList<MyWrite>>()
    val mypageWriteData: LiveData<MutableList<MyWrite>>
        get() = _mypageWriteData

    private val _scrapFilter = MutableLiveData<String>()
    val scrapFilter: LiveData<String>
        get() = _scrapFilter

    fun setScrapFilter(scrapFilter: String) {
        _scrapFilter.value = scrapFilter
    }

    private val _mywriteFilter = MutableLiveData<MyWriteFilter>()
    val mywriteFilter: LiveData<MyWriteFilter>
        get() = _mywriteFilter

    fun setWriteFilter(range: String, category: String) {
        val myfilter = MyWriteFilter(range, category)
        _mywriteFilter.value = myfilter
    }

    fun setDummyWrite() {
        val dummyWrite = listOf(
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "으음",
                time = "5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "행복",
                time = "5분 전",
                isSecret = true
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "사랑",
                time = "5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "미래",
                time = "5분 전",
                isSecret = false
            )
        )
        _mypageWriteData.value = dummyWrite.toMutableList()
    }

    private val _mypageScrapData = MutableLiveData<MutableList<MyScrap>>()
    val mypageScrapData: LiveData<MutableList<MyScrap>>
        get() = _mypageScrapData

    private val _profileUri = MutableLiveData<Uri>()
    val profileUri: LiveData<Uri>
        get() = _profileUri

    fun setProfileUri(uri: Uri) {
        _profileUri.value = uri
    }

    fun setDummyScrap() {
        val dummyScrap = listOf(
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man"
            )
        )
        _mypageScrapData.value = dummyScrap.toMutableList()
    }

    fun addDummyScrap() {
        val dummyScrap = listOf(
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man1"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man2"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man3"
            ),
            MyScrap(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "[ 미래에 관한 질문 ]",
                time = "5분 전",
                userId = "iron man4"
            )
        )
        _mypageScrapData.value?.addAll(dummyScrap.toMutableList())
    }

    fun addDummyWrite() {
        val dummyWrite = listOf(
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "으음",
                time = "5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "행복",
                time = "5분 전",
                isSecret = true
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "사랑",
                time = "5분 전",
                isSecret = false
            ),
            MyWrite(
                question = "요즘 내 삶엣어 가장 만족스러운 것은 무엇인가요?",
                categori = "미래",
                time = "5분 전",
                isSecret = false
            )
        )
        _mypageWriteData.value?.addAll(dummyWrite.toMutableList())
    }

    private val _isScrapFilterClicked = MutableLiveData<Boolean>()
    val isScrapFilterClicked: LiveData<Boolean>
        get() = _isScrapFilterClicked


    fun scrapFilterOnClick() {
        _isScrapFilterClicked.value = true
    }

    fun scrapFilterOnClickFalse() {
        _isScrapFilterClicked.value = false
    }

    private val _isWriteFilterClicked = MutableLiveData<Boolean>()
    val isWriteFilterClicked: LiveData<Boolean>
        get() = _isWriteFilterClicked


    fun writeFilterOnClick() {
        _isWriteFilterClicked.value = true
    }

    fun writeFilterOnClickFalse() {
        _isWriteFilterClicked.value = false
    }

}