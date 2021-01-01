package com.teambeme.beme.reply.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityReplyBinding
import com.teambeme.beme.reply.adapter.ReplyParentAdapter
import com.teambeme.beme.reply.viewmodel.ReplyViewModel

class ReplyActivity : BindingActivity<ActivityReplyBinding>(R.layout.activity_reply) {
    private val replyViewModel: ReplyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val replyAdapter = ReplyParentAdapter(this, replyViewModel)
        binding.lifecycleOwner = this
        binding.rcvReplyParent.apply {
            adapter = replyAdapter
            layoutManager = LinearLayoutManager(this@ReplyActivity)
        }
        replyViewModel.setDummyParentReply()

        replyViewModel.replyParentData.observe(this) { it ->
            it.let { replyAdapter.replaceReplyList(it) }
        }

        replyViewModel.isOpenClicked.observe(this) {
            openClickListener(it)
        }

        replyViewModel.isAddClicked.observe(this) {
            addClickListener(it)
        }

        replyViewModel.isSecretClicked.observe(this) {
            secretClickListener(it)
        }
    }

    private fun openClickListener(isOpenClicked: Boolean) {
        if (isOpenClicked) {
            Toast.makeText(this, "cc", Toast.LENGTH_SHORT).show()
            replyViewModel.replyOpenClickedFalse()
        }
    }

    private fun addClickListener(isAddClicked: Boolean) {
        if (isAddClicked) {
            Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
            replyViewModel.addReplyClickedFalse()
        } else {
            Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
        }
    }

    private fun secretClickListener(isSecretClicked: Boolean) {
        if (isSecretClicked) {
            binding.imgReplySecret.setImageResource(R.drawable.ic_secret_on)
            Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            replyViewModel.secretButtonClickedFalse()
        }
    }
}