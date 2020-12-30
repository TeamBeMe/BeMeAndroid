package com.teambeme.beme.reply.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReplyViewModel : ViewModel() {
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

    fun addReplyClicked() {
        _isAddClicked.value = true
    }

    private val _isSecretClicked = MutableLiveData<Boolean>()
    val isSecretClicked: LiveData<Boolean>
        get() = _isSecretClicked

    fun secretButtonClicked() {
        _isSecretClicked.value = true
    }

    private val _isOpenClicked = MutableLiveData<Boolean>()
    val isOpenClicked: LiveData<Boolean>
        get() = _isOpenClicked

    fun replyOpenClicked() {
        _isOpenClicked.value = true
    }

    init {
        _isOpenClicked.value = false
        _isAddClicked.value = false
        _isSecretClicked.value = false
    }
}