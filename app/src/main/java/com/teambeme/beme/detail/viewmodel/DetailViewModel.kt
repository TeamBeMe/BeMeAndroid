package com.teambeme.beme.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.detail.model.ReplyData
import com.teambeme.beme.detail.model.ReplyParentData
import com.teambeme.beme.detail.model.initReplyList

class DetailViewModel : ViewModel() {
    val answerText = MutableLiveData<String>()

    private val _isAddClicked = MutableLiveData<Boolean>()
    val isAddClicked: LiveData<Boolean>
        get() = _isAddClicked

    private val _isSecretClicked = MutableLiveData<Boolean>()
    val isSecretClicked: LiveData<Boolean>
        get() = _isSecretClicked

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
        _position.value = position
        _childposition.value = -1
    }

    private val _childposition = MutableLiveData<Int>()
    val childposition: LiveData<Int>
        get() = _childposition

    fun setChildPosition(position: Int, childposition: Int) {
        _childposition.value = childposition
        _position.value = position
    }

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
        _isSecretClicked.value = true
    }

    fun secretButtonClickedFalse() {
        _isSecretClicked.value = false
    }

    fun replyOpenClicked() {
        _isOpenClicked.value = true
    }

    fun replyOpenClickedFalse() {
        _isOpenClicked.value = false
    }

    private var dummyParentReply = mutableListOf(
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "a척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..f",
            txt_time = "12월24일",
            dataChild = initReplyList()
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..f",
            txt_time = "12월22일"
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..",
            txt_time = "12월24일",
            dataChild = initReplyList()
        )
    )

    fun getId(): String {
        return dummyParentReply[position.value!!].txt_id
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
        answerText.value = dummyParentReply[position.value!!].txt_comment
    }

    fun changeParentReplyComment() {
        dummyParentReply[position.value!!].txt_comment = answerText.value!!
        _replyParentData.value = dummyParentReply.toMutableList()
        answerText.value = ""
    }

    private val _isChildChangeClicked = MutableLiveData<Boolean>()
    val isChildChangeClicked: LiveData<Boolean>
        get() = _isChildChangeClicked

    fun setChildChangeClicked() {
        _isChildChangeClicked.value = true
    }

    fun changeChildClickedFalse() {
        _isChildChangeClicked.value = false
    }

    fun getChildReplyComment() {
        answerText.value =
            dummyParentReply[position.value!!].dataChild[childposition.value!!].txt_comment
    }

    fun changeChildReplyComment() {
        dummyParentReply[position.value!!].dataChild[childposition.value!!].txt_comment =
            answerText.value!!
        _replyParentData.value = dummyParentReply.toMutableList()
        answerText.value = ""
    }

    private val _replyParentData = MutableLiveData<MutableList<ReplyParentData>>()
    val replyParentData: LiveData<MutableList<ReplyParentData>>
        get() = _replyParentData

    fun deleteDummyParentReply(position: Int) {
        dummyParentReply.removeAt(position)
        _replyParentData.value = dummyParentReply.toMutableList()
    }

    fun deleteDummyReply(position: Int, childposition: Int) {
        dummyParentReply[position].dataChild.removeAt(childposition)
        _replyParentData.value = dummyParentReply.toMutableList()
    }

    fun setDummyParentReply() {

        _replyParentData.value = dummyParentReply.toMutableList()
    }

    private val _replyData = MutableLiveData<MutableList<ReplyData>>()
    val replyData: LiveData<MutableList<ReplyData>>
        get() = _replyData

    fun setChildData(child: List<ReplyData>) {
        _replyData.value = child.toMutableList()
    }

    fun setDummyReply() {
        val dummyReply = listOf(
            ReplyData(
                txt_id = "asdf",
                txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
                txt_time = "12월24일"
            ),
            ReplyData(
                txt_id = "asdf",
                txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
                txt_time = "12월24일"
            ),
            ReplyData(
                txt_id = "asdf",
                txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
                txt_time = "12월24일"
            )
        )
        _replyData.value = dummyReply.toMutableList()
    }

    fun addParentReply() {
        val dummyReply = ReplyParentData("asdf", answerText.value!!, "12.243")
        dummyParentReply.add(dummyReply)
        _replyParentData.value = dummyParentReply.toMutableList()
        answerText.value = ""
    }

    fun addChildReply() {
        val dummyReply = ReplyData("대댓글 추가", answerText.value!!, "12월24일")
        when {
            dummyParentReply[replyPosition.value!!].dataChild.size == 0 -> {
                dummyParentReply[replyPosition.value!!].dataChild.add(dummyReply)
            }
            dummyParentReply[replyPosition.value!!].dataChild[0].txt_id == "" -> {
                dummyParentReply[replyPosition.value!!].dataChild.add(dummyReply)
                dummyParentReply[replyPosition.value!!].dataChild.removeAt(0)
            }
            else -> {
                dummyParentReply[replyPosition.value!!].dataChild.add(dummyReply)
            }
        }
        _replyParentData.value = dummyParentReply.toMutableList()
        answerText.value = ""
    }
}