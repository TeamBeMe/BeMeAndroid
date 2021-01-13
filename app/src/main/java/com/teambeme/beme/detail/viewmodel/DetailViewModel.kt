package com.teambeme.beme.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.detail.model.*
import com.teambeme.beme.detail.repository.DetailRepository
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val detailRepository: DetailRepository) : ViewModel() {
    val answerText = MutableLiveData<String>()

    var copyChildData: MutableList<ResponseDetail.Data.Comment.Children> = mutableListOf()

    var copyReplyData: MutableList<ResponseDetail.Data.Comment> = mutableListOf()

    private val _detailData = MutableLiveData<ResponseDetail.Data>()
    val detailData: LiveData<ResponseDetail.Data>
        get() = _detailData

    private val _isAddClicked = MutableLiveData<Boolean>()
    val isAddClicked: LiveData<Boolean>
        get() = _isAddClicked

    private val _isPublic = MutableLiveData<Boolean>()
    val isPublic: LiveData<Boolean>
        get() = _isPublic

    private val _isOpenClicked = MutableLiveData<Boolean>()
    val isOpenClicked: LiveData<Boolean>
        get() = _isOpenClicked

    private val _isAddChildReplyClicked = MutableLiveData<Boolean>()
    val isAddChildReplyClicked: LiveData<Boolean>
        get() = _isAddChildReplyClicked

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int>
        get() = _position

    fun setPosition(position: Int) {
        if (copyReplyData[position].isAuthor) {
            authority = MY_REPLY
        } else if (detailData.value!!.isAuthor) {
            authority = MY_OTHER_REPLY
        } else {
            authority = OTHER_REPLY
        }
        _position.value = position
        _childposition.value = -1
    }

    private val _childposition = MutableLiveData<Int>()
    val childposition: LiveData<Int>
        get() = _childposition

    fun setChildPosition(position: Int, childposition: Int) {
        if (copyReplyData[position].children[childposition].isAuthor) {
            authority = MY_REPLY
        } else if (detailData.value!!.isAuthor) {
            authority = MY_OTHER_REPLY
        } else {
            authority = OTHER_REPLY
        }
        _childposition.value = childposition
        _position.value = position
    }

    var authority = 0

    private val _replyPosition = MutableLiveData<Int>()
    val replyPosition: LiveData<Int>
        get() = _replyPosition

    fun setReplyPosition(position: Int) {
        _replyPosition.value = position
    }

    fun addReplyClicked() {
        _isAddClicked.value = true
        Log.d("check", "check")
    }

    fun addReplyClickedFalse() {
        _isAddClicked.value = false
    }

    fun addReplyChildClicked() {
        _isAddChildReplyClicked.value = true
    }

    fun addReplyChildclickedFalse() {
        _isAddChildReplyClicked.value = false
    }

    fun secretButtonClicked() {
        _isPublic.value = _isPublic.value != true
    }

    fun secretButtonClickedFalse() {
        _isPublic.value = false
    }

    fun replyOpenClicked() {
        _isOpenClicked.value = true
    }

    fun replyOpenClickedFalse() {
        _isOpenClicked.value = false
    }

    fun getId(): String {
        return if (copyReplyData[replyPosition.value!!].isVisible)
            copyReplyData[replyPosition.value!!].userNickname
        else
            "익명"
    }

    fun requestDetail(answerId: Int) {
        detailRepository.getDetail(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            answerId
        )
            .enqueue(object :
                Callback<ResponseDetail> {
                override fun onResponse(
                    call: Call<ResponseDetail>,
                    response: Response<ResponseDetail>
                ) {
                    if (response.isSuccessful) {
                        copyReplyData = response.body()!!.data?.comment.toMutableList()
                        _detailData.value = response.body()!!.data
                        _canReply.value = response.body()!!.data.commentBlockedFlag
                        _isScrapped.value = response.body()!!.data.isScrapped
                        _replyParentData.value = copyReplyData
                    }
                }

                override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    private val _isChangeClicked = MutableLiveData<Boolean>()
    val isChangeClicked: LiveData<Boolean>
        get() = _isChangeClicked

    fun setChangeClicked() {
        _isChangeClicked.value = true
    }

    fun changeClickedFalse() {
        _isChangeClicked.value = false
    }

    fun getParentReplyComment() {
        answerText.value = copyReplyData[position.value!!].content
    }

    fun changeParentReply() {
        detailRepository.putReply(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            copyReplyData[position.value!!].id, answerText.value!!
        )
            .enqueue(object :
                Callback<ResponsePutReply> {
                override fun onResponse(
                    call: Call<ResponsePutReply>,
                    response: Response<ResponsePutReply>
                ) {
                    if (response.isSuccessful) {
                        val responseData = ResponseDetail.Data.Comment(
                            response.body()!!.data.answerId,
                            copyReplyData[position.value!!].children,
                            response.body()!!.data.content,
                            response.body()!!.data.createdAt,
                            response.body()!!.data.id,
                            response.body()!!.data.isAuthor,
                            response.body()!!.data.isVisible,
                            response.body()!!.data.publicFlag,
                            response.body()!!.data.updatedAt,
                            response.body()!!.data.userId,
                            response.body()!!.data.profileImg,
                            response.body()!!.data.userNickname
                        )
                        copyReplyData[position.value!!] = responseData
                        _replyParentData.value = copyReplyData.toMutableList()
                        answerText.value = ""
                    }
                }

                override fun onFailure(call: Call<ResponsePutReply>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun changeChildReply() {
        detailRepository.putReply(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            copyReplyData[position.value!!].children[childposition.value!!].id, answerText.value!!
        )
            .enqueue(object :
                Callback<ResponsePutReply> {
                override fun onResponse(
                    call: Call<ResponsePutReply>,
                    response: Response<ResponsePutReply>
                ) {
                    if (response.isSuccessful) {
                        val responseData = ResponseDetail.Data.Comment.Children(
                            response.body()!!.data.answerId, response.body()!!.data.content,
                            response.body()!!.data.createdAt, response.body()!!.data.id,
                            response.body()!!.data.isAuthor, response.body()!!.data.isVisible,
                            response.body()!!.data.parentId, response.body()!!.data.publicFlag,
                            response.body()!!.data.updatedAt, response.body()!!.data.userId,
                            response.body()!!.data.profileImg, response.body()!!.data.userNickname
                        )
                        copyChildData[childposition.value!!] = responseData
                        copyReplyData[position.value!!].children = copyChildData
                        _replyParentData.value = copyReplyData.toMutableList()
                        answerText.value = ""
                    }
                }

                override fun onFailure(call: Call<ResponsePutReply>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun changeParentReplyComment() {
        copyReplyData[position.value!!].content = answerText.value!!
        _replyParentData.value = copyReplyData.toMutableList()
        answerText.value = ""
    }

    private val _isChildChangeClicked = MutableLiveData<Boolean>()
    val isChildChangeClicked: LiveData<Boolean>
        get() = _isChildChangeClicked

    private val _isScrapped = MutableLiveData<Boolean>()
    val isScrapped: LiveData<Boolean>
        get() = _isScrapped

    fun setChildChangeClicked() {
        _isChildChangeClicked.value = true
    }

    fun changeChildClickedFalse() {
        _isChildChangeClicked.value = false
    }

    fun getChildReplyComment() {
        answerText.value =
            copyReplyData[position.value!!].children[childposition.value!!].content
    }

    fun changeChildReplyComment() {
        copyReplyData[position.value!!].children[childposition.value!!].content =
            answerText.value!!
        _replyParentData.value = copyReplyData.toMutableList()
        answerText.value = ""
    }

    private val _replyParentData = MutableLiveData<MutableList<ResponseDetail.Data.Comment>>()
    val replyParentData: LiveData<MutableList<ResponseDetail.Data.Comment>>
        get() = _replyParentData

    fun deleteParentReply(position: Int) {
        deleteReply(copyReplyData[position].id)
        copyReplyData.removeAt(position)
        _replyParentData.value = copyReplyData.toMutableList()
    }

    private val _isDeleteReply = MutableLiveData<Boolean>()
    val isDeleteReply: LiveData<Boolean>
        get() = _isDeleteReply

    fun putScrap() {
        detailRepository.putScrap(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            detailData.value!!.id
        ).enqueue(object : Callback<ResponseScrap> {
            override fun onResponse(
                call: Call<ResponseScrap>,
                response: Response<ResponseScrap>
            ) {
                if (response.isSuccessful) {
                    setScrapped()
                }
            }

            override fun onFailure(call: Call<ResponseScrap>, t: Throwable) {
                Log.d("Network Fail", t.message.toString())
            }
        })
    }

    fun setScrapped() {
        _isScrapped.value = isScrapped.value != true
    }

    private val _isScrapClicked = MutableLiveData<Boolean>()
    val isScrapClicked: LiveData<Boolean>
        get() = _isScrapClicked

    fun scrapClicked() {
        _isScrapClicked.value = _isScrapClicked.value != true
        Log.d("test", "ASGSD")
    }

    private fun deleteReply(commentId: Int) {
        detailRepository.deleteReply(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            commentId
        )
            .enqueue(object :
                Callback<ResponseDeleteReply> {
                override fun onResponse(
                    call: Call<ResponseDeleteReply>,
                    response: Response<ResponseDeleteReply>
                ) {
                    if (response.isSuccessful) {
                        _isDeleteReply.value = true
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteReply>, t: Throwable) {
                    _isDeleteReply.value = false
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    private val _isDeleteAnswer = MutableLiveData<Boolean>()
    val isDeleteAnswer: LiveData<Boolean>
        get() = _isDeleteAnswer

    private val _canReply = MutableLiveData<Boolean>()
    val canReply: LiveData<Boolean>
        get() = _canReply

    fun deleteAnswer() {
        detailRepository.deleteAnswer(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            detailData.value!!.id
        )
            .enqueue(object :
                Callback<ResponseDeleteAnswer> {
                override fun onResponse(
                    call: Call<ResponseDeleteAnswer>,
                    response: Response<ResponseDeleteAnswer>
                ) {
                    if (response.isSuccessful) {
                        _isDeleteAnswer.value = true
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteAnswer>, t: Throwable) {
                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun deleteChildReply(position: Int, childposition: Int) {
        copyChildData = copyReplyData[position].children.toMutableList()
        deleteReply(copyReplyData[position].children[childposition].id)
        copyChildData.removeAt(childposition)
        copyReplyData[position].children = copyChildData
        _replyParentData.value = copyReplyData.toMutableList()
    }

    fun setDummyParentReply() {

        _replyParentData.value = copyReplyData.toMutableList()
    }

    private val _replyData = MutableLiveData<MutableList<ResponseDetail.Data.Comment.Children>>()
    val replyData: LiveData<MutableList<ResponseDetail.Data.Comment.Children>>
        get() = _replyData

    fun setChildData(child: List<ResponseDetail.Data.Comment.Children>) {
        _replyData.value = child.toMutableList()
    }

    fun addParentReply() {
        var reply: ResponseDetail.Data.Comment
        detailRepository.postReply(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            detailData.value!!.id, answerText.value!!, isPublic.value!!, null
        )
            .enqueue(object :
                Callback<ResponsePostReply> {
                override fun onResponse(
                    call: Call<ResponsePostReply>,
                    response: Response<ResponsePostReply>
                ) {
                    if (response.isSuccessful) {
                        reply = ResponseDetail.Data.Comment(
                            response.body()!!.data.answerId,
                            listOf(),
                            response.body()!!.data.content,
                            response.body()!!.data.createdAt,
                            response.body()!!.data.id,
                            response.body()!!.data.isAuthor,
                            response.body()!!.data.isVisible,
                            response.body()!!.data.publicFlag,
                            response.body()!!.data.updatedAt,
                            response.body()!!.data.userId,
                            response.body()!!.data.profileImg,
                            response.body()!!.data.userNickname
                        )
                        copyReplyData.add(reply)
                        _replyParentData.value = copyReplyData.toMutableList()
                        answerText.value = ""
                    }
                }

                override fun onFailure(call: Call<ResponsePostReply>, t: Throwable) {

                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    fun addChildReply() {
        var childrenReply: ResponseDetail.Data.Comment.Children
        detailRepository.postReply(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjEwMDk5MjQwLCJleHAiOjE2MzYwMTkyNDAsImlzcyI6ImJlbWUifQ.JeYfzJsg-kdatqhIOqfJ4oXUvUdsiLUaGHwLl1mJRvQ",
            detailData.value!!.id,
            answerText.value!!,
            isPublic.value!!,
            copyReplyData[replyPosition.value!!].id
        )
            .enqueue(object :
                Callback<ResponsePostReply> {
                override fun onResponse(
                    call: Call<ResponsePostReply>,
                    response: Response<ResponsePostReply>
                ) {
                    if (response.isSuccessful) {
                        childrenReply = ResponseDetail.Data.Comment.Children(
                            response.body()!!.data.answerId, response.body()!!.data.content,
                            response.body()!!.data.createdAt, response.body()!!.data.id,
                            response.body()!!.data.isAuthor, response.body()!!.data.isVisible,
                            response.body()!!.data.parentId, response.body()!!.data.publicFlag,
                            response.body()!!.data.updatedAt, response.body()!!.data.userId,
                            response.body()!!.data.profileImg, response.body()!!.data.userNickname
                        )
                        copyChildData =
                            copyReplyData[replyPosition.value!!].children.toMutableList()
                        copyChildData.add(childrenReply)
                        copyReplyData[replyPosition.value!!].children = copyChildData
                        _replyParentData.value = copyReplyData.toMutableList()
                        answerText.value = ""
                    }
                }

                override fun onFailure(call: Call<ResponsePostReply>, t: Throwable) {

                    Log.d("Network Fail", t.message.toString())
                }
            })
    }

    companion object {
        val MY_REPLY = 1
        val MY_OTHER_REPLY = 2
        val OTHER_REPLY = 3
    }
}