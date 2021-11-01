package com.teambeme.beme.mypage.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.domain.repository.MyPageRepository
import com.teambeme.beme.mypage.model.*
import com.teambeme.beme.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private var copyMyAnswerList: MutableList<ResponseMyAnswer.Data.Answer> = mutableListOf()
    private var copyMyScrapList: MutableList<ResponseMyScrap.Data.Answer> = mutableListOf()

    private val _mypageWriteData = MutableLiveData<MutableList<ResponseMyAnswer.Data.Answer>>()
    val mypageWriteData: LiveData<MutableList<ResponseMyAnswer.Data.Answer>>
        get() = _mypageWriteData

    private val _myProfileInfo = MutableLiveData<ResponseMyProfile.Data>()
    val myProfileInfo: LiveData<ResponseMyProfile.Data>
        get() = _myProfileInfo

    private val _writeScrollUp = SingleLiveEvent<Unit>()
    val writeScrollUp: LiveData<Unit>
        get() = _writeScrollUp

    private val _scrapScrollUp = SingleLiveEvent<Unit>()
    val scrapScrollUp: LiveData<Unit>
        get() = _scrapScrollUp

    private var page: Int = 2

    var tempPage: Int = 1
        private set

    private var scrapPage = 2

    var scrapTempPage: Int = 1
        private set

    fun clearCopyMyAnswerList() {
        copyMyAnswerList.clear()
    }

    fun clearCopyMyScrapList() {
        copyMyScrapList.clear()
    }

    private val _scrapFilter = MutableLiveData<MyWriteFilter>()
    val scrapFilter: LiveData<MyWriteFilter>
        get() = _scrapFilter

    private val _myQuery = MutableLiveData<String?>()
    val myQuery: LiveData<String?>
        get() = _myQuery

    private val _scrapQuery = MutableLiveData<String?>()
    val scrapQuery: LiveData<String?>
        get() = _scrapQuery

    fun setMyQuery(query: String) {
        _myQuery.value = query
    }

    fun deleteMyQuery() {
        _myQuery.value = null
    }

    fun deleteScrapQuery() {
        _scrapQuery.value = null
    }

    fun initPage() {
        page = 2
    }

    fun initScrapPage() {
        scrapPage = 2
    }

    fun setScrapFilter(range: String?, category: Int?) {
        val filter = MyWriteFilter(range, category)
        _scrapFilter.value = filter
    }

    private val _mywriteFilter = MutableLiveData<MyWriteFilter>()
    val mywriteFilter: LiveData<MyWriteFilter>
        get() = _mywriteFilter

    fun setWriteFilter(range: String?, category: Int?) {
        val myfilter = MyWriteFilter(range, category)
        _mywriteFilter.value = myfilter
    }

    fun putProfiles(multipart: MultipartBody.Part?) {
        myPageRepository.putProfile(
            multipart
        ).enqueue(object : Callback<ResponseProfile> {
            override fun onResponse(
                call: Call<ResponseProfile>,
                response: Response<ResponseProfile>
            ) {
                if (response.isSuccessful) {
                    Log.d("aa", "Asdfsdf")
                }
            }

            override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    private val _isAnswerMax = MutableLiveData<Boolean>()
    val isAnswerMax: LiveData<Boolean>
        get() = _isAnswerMax

    private val _isScrapMax = MutableLiveData<Boolean>()
    val isScrapMax: LiveData<Boolean>
        get() = _isScrapMax

    private val _isPublic = MutableLiveData<Boolean>()
    val isPublic: LiveData<Boolean>
        get() = _isPublic

    fun Boolean.toInt() = if (this) 0 else 1

    private fun setPublic() {
        _isPublic.value = _isPublic.value != true
    }

    fun putPublic(id: Int, flag: Boolean) {
        myPageRepository.putPublic(
            id,
            flag.toInt()
        ).enqueue(object :
                Callback<ResponsePublic> {
                override fun onResponse(
                    call: Call<ResponsePublic>,
                    response: Response<ResponsePublic>
                ) {
                    if (response.isSuccessful) {
                    }
                }

                override fun onFailure(call: Call<ResponsePublic>, t: Throwable) {
                }
            })
    }

    private val _isAnswerEmpty = MutableLiveData<Boolean>()
    val isAnswerEmpty: LiveData<Boolean>
        get() = _isAnswerEmpty

    private val _isScrapEmpty = MutableLiveData<Boolean>()
    val isScrapEmpty: LiveData<Boolean>
        get() = _isScrapEmpty

    fun getMyAnswer(pageNum: Int) {
        myPageRepository.getMyAnswer(
            mywriteFilter.value?.range,
            mywriteFilter.value?.category,
            myQuery.value,
            pageNum
        ).enqueue(object :
                Callback<ResponseMyAnswer> {
                override fun onResponse(
                    call: Call<ResponseMyAnswer>,
                    response: Response<ResponseMyAnswer>
                ) {
                    if (response.isSuccessful) {
                        if (pageNum != page) {
                            if (tempPage == 1) {
                                clearCopyMyAnswerList()
                            }
                            copyMyAnswerList.addAll(response.body()!!.data.answers.toMutableList())
                            tempPage++
                            if (response.body()!!.data.answers.isNotEmpty()) {
                                _isAnswerMax.value = response.body()!!.data.answers.size != 10
                            } else {
                                _isAnswerMax.value = true
                            }
                            getMyAnswer(tempPage)
                        } else {
                            _mypageWriteData.value = copyMyAnswerList.toMutableList()
                            tempPage = 1
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMyAnswer>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun getPlusMyAnswer() {
        myPageRepository.getMyAnswer(
            mywriteFilter.value?.range,
            mywriteFilter.value?.category,
            myQuery.value,
            page
        ).enqueue(object :
                Callback<ResponseMyAnswer> {
                override fun onResponse(
                    call: Call<ResponseMyAnswer>,
                    response: Response<ResponseMyAnswer>
                ) {
                    if (response.isSuccessful) {
                        copyMyAnswerList.addAll(response.body()!!.data.answers.toMutableList())
                        _mypageWriteData.value = copyMyAnswerList.toMutableList()
                        if (response.body()!!.data.answers.isNotEmpty()) {
                            page++
                            _isAnswerMax.value = response.body()!!.data.answers.size != 10
                        } else {
                            _isAnswerMax.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMyAnswer>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun setScrapQuery(query: String) {
        _scrapQuery.value = query
    }

    fun getMyProfile() {
        myPageRepository.getMyProfile().enqueue(object :
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

    fun getMyScrap(pageNum: Int) {
        myPageRepository.getMyScrap(
            scrapFilter.value?.range,
            scrapFilter.value?.category,
            scrapQuery.value,
            pageNum
        ).enqueue(object :
                Callback<ResponseMyScrap> {
                override fun onResponse(
                    call: Call<ResponseMyScrap>,
                    response: Response<ResponseMyScrap>
                ) {
                    if (response.isSuccessful) {
                        Log.d(
                            "recursion_mypage",
                            "pageNum : $pageNum, page : $page, tempPage : $scrapTempPage"
                        )
                        if (pageNum != scrapPage) {
                            if (scrapTempPage == 1) {
                                clearCopyMyScrapList()
                            }
                            copyMyScrapList.addAll(response.body()!!.data.answers.toMutableList())
                            scrapTempPage++
                            if (response.body()!!.data.answers.isNotEmpty()) {
                                _isScrapMax.value = response.body()!!.data.answers.size != 10
                            } else {
                                _isScrapMax.value = true
                            }
                            getMyScrap(scrapTempPage)
                        } else {
                            _mypageScrapData.value = copyMyScrapList.toMutableList()
                            scrapTempPage = 1
                        }
                        Log.d(
                            "recursion_mypage",
                            " tempPage : $scrapTempPage"
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseMyScrap>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun getPlusMyScrap() {
        myPageRepository.getMyScrap(
            scrapFilter.value?.range,
            scrapFilter.value?.category,
            scrapQuery.value,
            page
        ).enqueue(object :
                Callback<ResponseMyScrap> {
                override fun onResponse(
                    call: Call<ResponseMyScrap>,
                    response: Response<ResponseMyScrap>
                ) {
                    if (response.isSuccessful) {
                        copyMyScrapList.addAll(response.body()!!.data.answers.toMutableList())
                        _mypageScrapData.value = copyMyScrapList.toMutableList()
                        if (response.body()!!.data.answers.isNotEmpty()) {
                            scrapPage++
                            _isScrapMax.value = response.body()!!.data.answers.size != 10
                        } else {
                            _isScrapMax.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMyScrap>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    private val _mypageScrapData = MutableLiveData<MutableList<ResponseMyScrap.Data.Answer>>()
    val mypageScrapData: LiveData<MutableList<ResponseMyScrap.Data.Answer>>
        get() = _mypageScrapData

    private val _profileUri = MutableLiveData<Uri?>()
    val profileUri: LiveData<Uri?>
        get() = _profileUri

    fun setProfileUri(uri: Uri?) {
        _profileUri.value = uri
    }

    fun setProfileNull() {
        _myProfileInfo.value!!.profileImg = null
    }

    private val _profileString = MutableLiveData<String>()
    val profileString: LiveData<String>
        get() = _profileString

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

    fun scrollUp(selectedTabPosition: Int) {
        when (selectedTabPosition) {
            0 -> _writeScrollUp.call()
            else -> _scrapScrollUp.call()
        }
    }
}
