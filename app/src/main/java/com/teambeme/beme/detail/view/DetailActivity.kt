package com.teambeme.beme.detail.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityDetailBinding
import com.teambeme.beme.detail.adapter.ReplyAdapter
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.detailViewModel = detailViewModel
        val replyAdapter = ReplyAdapter(this, detailViewModel)
        binding.lifecycleOwner = this
        binding.rcvDetailParent.apply {
            adapter = replyAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }
        detailViewModel.setDummyParentReply()
        detailViewModel.replyParentData.observe(this) { replyParentData ->
            replyParentData.let {
                replyAdapter.replaceReplyList(replyParentData.toMutableList())
            }
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
        detailViewModel.position.observe(this) {
            positionListener(it)
        }
        detailViewModel.isChangeClicked.observe(this) {
            changeClickListener(it)
        }
        detailViewModel.isChildChangeClicked.observe(this) {
            changeChildclickListener(it)
        }
        detailViewModel.isAddChildReplyClicked.observe(this) {
            addChildReplyListener(it)
        }
        binding.btnScrapBack.setOnClickListener { finish() }
    }

    private fun positionListener(position: Int) {
        // if(myId==replyId) -> MyReplyFragment else if(myId==writeId)
        // -> MyOtherFragment else OtherFragment   나중을위한주석

        val bottomSheetFragment = BottomMyReplyFragment(false)
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }

    private fun openClickListener(isOpenClicked: Boolean) {
        if (isOpenClicked) {
            Toast.makeText(this, "cc", Toast.LENGTH_SHORT).show()
            detailViewModel.replyOpenClickedFalse()
        }
    }

    private fun addClickListener(isAddClicked: Boolean) {
        if (detailViewModel.answerText.value == "") {
            Toast.makeText(this, "빈 댓글은 달 수 없습니다", Toast.LENGTH_SHORT).show()
        } else {
            when {
                detailViewModel.isChangeClicked.value == true -> {
                    detailViewModel.changeParentReplyComment()
                    detailViewModel.changeClickedFalse()
                    binding.constraintDetailSnakbar.visibility = View.GONE
                    hideKeyboard()
                }
                detailViewModel.isChildChangeClicked.value == true -> {
                    detailViewModel.changeChildReplyComment()
                    detailViewModel.changeChildClickedFalse()
                    binding.constraintDetailSnakbar.visibility = View.GONE
                    hideKeyboard()
                }
                detailViewModel.isAddChildReplyClicked.value == true -> {
                    detailViewModel.addChildReply()
                    detailViewModel.addReplyChildclickedFalse()
                    binding.constraintDetailSnakbar.visibility = View.GONE
                    hideKeyboard()
                }
                else -> {
                    detailViewModel.addParentReply()
                    detailViewModel.addReplyClickedFalse()
                    hideKeyboard()
                }
            }
        }
    }

    private fun addChildReplyListener(isClicked: Boolean) {
        if (isClicked) {
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            binding.txtDetailMessage.text = "${detailViewModel.getId()} 님에게 답글을 남기는 중"
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.addReplyChildclickedFalse()
                detailViewModel.answerText.value = ""
            }
            focusKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.edttextDetailContent.windowToken, 0)
    }

    private fun focusKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    private fun changeChildclickListener(isChildChangeClicked: Boolean) {
        if (isChildChangeClicked) {
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            binding.txtDetailMessage.text = "댓글을 수정중입니다"
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.changeChildClickedFalse()
                detailViewModel.answerText.value = ""
            }
            focusKeyboard()
        }
    }

    private fun changeClickListener(isChangeClicked: Boolean) {
        if (isChangeClicked) {
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            binding.txtDetailMessage.text = "댓글을 수정중입니다"
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.changeClickedFalse()
                detailViewModel.answerText.value = ""
            }
            focusKeyboard()
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