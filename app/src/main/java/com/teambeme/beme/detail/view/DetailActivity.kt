package com.teambeme.beme.detail.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityDetailBinding
import com.teambeme.beme.detail.adapter.ReplyParentAdapter
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.detailViewModel = detailViewModel
        val replyAdapter = ReplyParentAdapter(this, detailViewModel)
        binding.lifecycleOwner = this
        binding.rcvDetailParent.apply {
            adapter = replyAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }
        detailViewModel.setDummyParentReply()

        detailViewModel.replyParentData.observe(this) { it ->
            it.let { replyAdapter.replaceReplyList(it) }
        }

        detailViewModel.isOpenClicked.observe(this) {
            openClickListener(it)
        }

        detailViewModel.isAddClicked.observe(this) {
            addClickListener(it)
        }

        detailViewModel.isSecretClicked.observe(this) {
            secretClickListener(it)
        }
    }

    private fun openClickListener(isOpenClicked: Boolean) {
        if (isOpenClicked) {
            Toast.makeText(this, "cc", Toast.LENGTH_SHORT).show()
            detailViewModel.replyOpenClickedFalse()
        }
    }

    private fun addClickListener(isAddClicked: Boolean) {
        if (isAddClicked) {
            Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
            detailViewModel.addReplyClickedFalse()
        } else {
            Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
        }
    }

    private fun secretClickListener(isSecretClicked: Boolean) {
        if (isSecretClicked) {
            binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_on)
            Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            detailViewModel.secretButtonClickedFalse()
        }
    }
}