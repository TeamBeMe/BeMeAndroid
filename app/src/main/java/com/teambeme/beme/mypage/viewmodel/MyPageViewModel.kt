package com.teambeme.beme.mypage.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.mypage.model.*
import com.teambeme.beme.mypage.repository.MyPageRepository
import com.teambeme.beme.otherpage.model.ResponseOtherData
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MyPageViewModel(private val myPageRepository: MyPageRepository) : ViewModel() {
    private var copyMyAnswerList: MutableList<ResponseMyAnswer.Data.Answer> = mutableListOf()
    private var copyMyScrapList: MutableList<ResponseMyScrap.Data.Answer> = mutableListOf()

    private val _mypageWriteData = MutableLiveData<MutableList<ResponseMyAnswer.Data.Answer>>()
    val mypageWriteData: LiveData<MutableList<ResponseMyAnswer.Data.Answer>>
        get() = _mypageWriteData

    private val _myProfileInfo = MutableLiveData<ResponseMyProfile.Data>()
    val myProfileInfo: LiveData<ResponseMyProfile.Data>
        get() = _myProfileInfo

    private var page=1
    private var scrapPage=1

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

    //lateinit var multiBody:MultipartBody.Part

    fun putProfiles(multipart:MultipartBody.Part){
        val file = File(profileString.value)
        val fileReqBody = RequestBody.create(MediaType.parse("image/jpg"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileReqBody)
        var map = HashMap<String,@JvmSuppressWildcards RequestBody>()
        map.put("image",fileReqBody)
        myPageRepository.putProfile(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ"
            ,multipart
        ).enqueue(object : Callback<ResponseProfile> {
            override fun onResponse(
                call: Call<ResponseProfile>,
                response: Response<ResponseProfile>
            ) {
                if (response.isSuccessful) {
                    Log.d("aa","Asdfsdf")
                }
            }

            override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun getMyAnswer() {
        myPageRepository.getMyAnswer(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            null,
            null,
            null,
            page
        ).enqueue(object :
            Callback<ResponseMyAnswer> {
            override fun onResponse(
                call: Call<ResponseMyAnswer>,
                response: Response<ResponseMyAnswer>
            ) {
                if (response.isSuccessful) {
                    copyMyAnswerList = response.body()!!.data?.answers?.toMutableList()
                    _mypageWriteData.value=copyMyAnswerList.toMutableList()
                    /*when (page == response.body()!!.data.pageLen) {
                        true -> {
                            _isMax.value = true
                        }
                        else -> {
                            page++
                        }
                    }*/
                }
            }

            override fun onFailure(call: Call<ResponseMyAnswer>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun getMyProfile(){
        myPageRepository.getMyProfile(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ"
        ).enqueue(object :
            Callback<ResponseMyProfile> {
            override fun onResponse(
                call: Call<ResponseMyProfile>,
                response: Response<ResponseMyProfile>
            ) {
                if (response.isSuccessful) {
                    _myProfileInfo.value = response.body()!!.data
                }
            }

            override fun onFailure(call: Call<ResponseMyProfile>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun getMyScrap() {
        myPageRepository.getMyScrap(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            null,
            null,
            null,
            page
        ).enqueue(object :
            Callback<ResponseMyScrap> {
            override fun onResponse(
                call: Call<ResponseMyScrap>,
                response: Response<ResponseMyScrap>
            ) {
                if (response.isSuccessful) {
                    copyMyScrapList = response.body()!!.data?.answers?.toMutableList()
                    _mypageScrapData.value=copyMyScrapList.toMutableList()
                    /*when (page == response.body()!!.data.pageLen) {
                        true -> {
                            _isMax.value = true
                        }
                        else -> {
                            page++
                        }
                    }*/
                }
            }

            override fun onFailure(call: Call<ResponseMyScrap>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    /*fun setDummyWrite() {
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
    }*/

    private val _mypageScrapData = MutableLiveData<MutableList<ResponseMyScrap.Data.Answer>>()
    val mypageScrapData: LiveData<MutableList<ResponseMyScrap.Data.Answer>>
        get() = _mypageScrapData

    private val _profileUri = MutableLiveData<Uri>()
    val profileUri: LiveData<Uri>
        get() = _profileUri

    fun setProfileUri(uri: Uri) {
        _profileUri.value = uri
    }

    private val _profileString = MutableLiveData<String>()
    val profileString: LiveData<String>
        get() = _profileString

    fun setProfileString(uri: String) {
        _profileString.value = uri
    }

    /*fun setDummyScrap() {
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
    }*/

    /*fun addDummyScrap() {
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
    }*/

    /*fun addDummyWrite() {
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
    }*/

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