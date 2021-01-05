package com.teambeme.beme.detail.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.detail.model.ReplyData
import com.teambeme.beme.detail.model.ReplyParentData
import com.teambeme.beme.detail.model.initReplyList

class DetailViewModel : ViewModel() {

    private val _replyParentId = MutableLiveData<String>()
    val replyParentId: LiveData<String>
        get() = _replyParentId

    fun setReplyParentId(replyParentId: String) {
        _replyParentId.value = replyParentId
    }

    private val _replyParentTime = MutableLiveData<String>()
    val replyParentTime: LiveData<String>
        get() = _replyParentTime

    fun setReplyParentTime(replyParentTime: String) {
        _replyParentTime.value = replyParentTime
    }

    private val _replyParentComment = MutableLiveData<String>()
    val replyParentComment: LiveData<String>
        get() = _replyParentComment

    fun setReplyParentComment(replyParentComment: String) {
        _replyParentComment.value = replyParentComment
    }

    private val _replyChildId = MutableLiveData<String>()
    val replyChildId: LiveData<String>
        get() = _replyChildId

    fun setReplyChildId(replyChildId: String) {
        _replyChildId.value = replyChildId
    }

    private val _replyChildTime = MutableLiveData<String>()
    val replyChildTime: LiveData<String>
        get() = _replyChildTime

    fun setReplyChildTime(replyChildTime: String) {
        _replyChildTime.value = replyChildTime
    }

    private val _replyChildComment = MutableLiveData<String>()
    val replyChildComment: LiveData<String>
        get() = _replyChildComment

    fun setReplyChildComment(replyChildComment: String) {
        _replyChildComment.value = replyChildComment
    }

    val editContent = ObservableField<String>()

    private val _isAddClicked = MutableLiveData<Boolean>()
    val isAddClicked: LiveData<Boolean>
        get() = _isAddClicked

    private val _isSecretClicked = MutableLiveData<Boolean>()
    val isSecretClicked: LiveData<Boolean>
        get() = _isSecretClicked

    private val _isOpenClicked = MutableLiveData<Boolean>()
    val isOpenClicked: LiveData<Boolean>
        get() = _isOpenClicked


    fun addReplyClicked() {
        _isAddClicked.value = true
        Log.d("check", "check")
    }

    fun addReplyClickedFalse() {
        _isAddClicked.value = false
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

    private val _replyParentData = MutableLiveData<MutableList<ReplyParentData>>()
    val replyParentData: LiveData<MutableList<ReplyParentData>>
        get() = _replyParentData

    fun setDummyParentReply() {
        val dummyParentReply = listOf(
            ReplyParentData(
                txt_id = "asdf",
                txt_comment = "a척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..f",
                txt_time = "12월24일",
                data_child = initReplyList()
            ),
            ReplyParentData(
                txt_id = "asdf",
                txt_comment = "척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..f",
                txt_time = "12월22일",

                ),
            ReplyParentData(
                txt_id = "asdf",
                txt_comment = "척박하고 각박한 세상에... 새소년의 눈을 들으며... 시험기간 내 마음을 달래주는 당신들의 목도리 이벤트를 참여합니다..",
                txt_time = "12월24일",
                data_child = initReplyList()
            )
        )
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


}