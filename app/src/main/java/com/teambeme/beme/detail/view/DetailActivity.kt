package com.teambeme.beme.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.DetailDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityDetailBinding
import com.teambeme.beme.detail.adapter.ReplyAdapter
import com.teambeme.beme.data.repository.DetailRepositoryImpl
import com.teambeme.beme.detail.viewmodel.DetailViewModel
import com.teambeme.beme.detail.viewmodel.DetailViewModel.Companion.MY_OTHER_REPLY
import com.teambeme.beme.detail.viewmodel.DetailViewModel.Companion.MY_REPLY
import com.teambeme.beme.detail.viewmodel.DetailViewModelFactory
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.otherpage.view.OtherPageActivity
import com.teambeme.beme.util.StatusBarUtil

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModelFactory =
        DetailViewModelFactory(DetailRepositoryImpl(DetailDataSourceImpl(RetrofitObjects.getDetailService())))
    private val detailViewModel: DetailViewModel by viewModels { detailViewModelFactory }
    private var answerId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        answerId = intent.getIntExtra("answerId", 0)
        val deleteBtnOtherAnswers = intent.getBooleanExtra("deleteBtnOtherAnswers", false)
        setBtnVisible(deleteBtnOtherAnswers)
        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this
        setAdapter(answerId)
        clickListener(answerId)
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.requestDetail(answerId)
    }

    private fun setBtnVisible(deleteBtnOtherAnswer: Boolean) {
        if (deleteBtnOtherAnswer) {
            binding.btnDetailShowmore.visibility = View.GONE
        } else {
            binding.btnDetailShowmore.visibility = View.VISIBLE
        }
    }

    private fun setAdapter(answerId: Int) {
        val replyAdapter = ReplyAdapter(this, detailViewModel)
        binding.rcvDetailParent.apply {
            adapter = replyAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }
        detailViewModel.replyParentData.observe(this) { replyParentData ->
            replyParentData.let {
                replyAdapter.replaceReplyList(replyParentData.toMutableList())
            }
        }
    }

    private fun clickListener(answerId: Int) {
        detailViewModel.isScrapClicked.observe(this) {
            scrapListener()
        }
        binding.btnDetailShowmore.setOnClickListener {
            val intent = Intent(this, ExploreDetailActivity::class.java)
            intent.putExtra("questionId", detailViewModel.detailData.value!!.questionId)
            intent.putExtra("otherMindsTitle", detailViewModel.detailData.value!!.question)
            startActivity(intent)
        }
        detailViewModel.isDeleteReply.observe(this) { deleteReplyListener(it) }

        detailViewModel.isOpenClicked.observe(this) {
            openClickListener(it)
        }

        detailViewModel.isAddClicked.observe(this) {
            addClickListener(it)
        }

        detailViewModel.isPublic.observe(this) {
            secretClickListener(it)
        }
        detailViewModel.position.observe(this) {
            positionListener()
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
        detailViewModel.isDeleteAnswer.observe(this) {
            deleteAnswerListener()
        }
        dotClickListener()
        detailViewModel.canReply.observe(this) {
            isPublicAnswerListener(it)
        }
        binding.btnScrapBack.setOnClickListener { finish() }
        profileClickListener()
        detailViewModel.secretButtonClickedFalse()
    }

    private fun profileClickListener() {
        binding.imgDetailProfile.setOnClickListener {
            val intent = Intent(this, OtherPageActivity::class.java)
            intent.putExtra("userId", detailViewModel.detailData.value!!.userId)
            startActivity(intent)
        }
    }

    private fun isPublicAnswerListener(canReply: Boolean) {
        if (canReply) {
            binding.constraintDetailReplybar.visibility = View.GONE
        } else {
            binding.constraintDetailReplybar.visibility = View.VISIBLE
        }
    }

    private fun deleteAnswerListener() {
        finish()
        Toast.makeText(this, "내 글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun dotClickListener() {
        binding.txtDetailDot.setOnClickListener {
            var bottomSheetFragment = BottomSheetDialogFragment()
            if (detailViewModel.detailData.value!!.isAuthor) {
                bottomSheetFragment = BottomMyReplyFragment(true)
            } else {
                bottomSheetFragment = BottomOtherReplyFragment()
            }
            bottomSheetFragment.show(
                supportFragmentManager,
                bottomSheetFragment.tag
            )
        }
    }

    private fun scrapListener() {
        if (detailViewModel.isScrapped.value == false) {
            binding.btnDetailScrap.setImageResource(R.drawable.ic_scrap_on_detail)
            Toast.makeText(this, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            binding.btnDetailScrap.setImageResource(R.drawable.ic_scrap_off_detail)
            Toast.makeText(this, "스크랩이 취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
        detailViewModel.putScrap()
    }

    private fun deleteReplyListener(isDelete: Boolean) {
        if (isDelete) {
            detailViewModel.secretButtonClickedFalse()
            Toast.makeText(this, "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun positionListener() {
        var bottomSheetFragment = BottomSheetDialogFragment()
        if (detailViewModel.authority == MY_REPLY) {
            bottomSheetFragment = BottomMyReplyFragment(false)
        } else if (detailViewModel.authority == MY_OTHER_REPLY) {
            bottomSheetFragment = BottomMyOtherReplyFragment()
        } else {
            bottomSheetFragment = BottomOtherReplyFragment()
        }
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }

    private fun openClickListener(isOpenClicked: Boolean) {
        if (isOpenClicked) {
            detailViewModel.replyOpenClickedFalse()
        }
    }

    private fun addClickListener(isAddClicked: Boolean) {
        if (detailViewModel.answerText.value == "") {
            Toast.makeText(this, "빈 댓글은 달 수 없습니다", Toast.LENGTH_SHORT).show()
        } else if (isAddClicked) {
            binding.imgDetailSecret.isEnabled = true

            when {
                detailViewModel.isChangeClicked.value == true -> {
                    detailViewModel.changeParentReply()
                    detailViewModel.changeClickedFalse()
                    binding.constraintDetailSnakbar.visibility = View.GONE
                    hideKeyboard()
                }
                detailViewModel.isChildChangeClicked.value == true -> {
                    detailViewModel.changeChildReply()
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
            detailViewModel.secretButtonClickedFalse()
        }
    }

    private fun addChildReplyListener(isClicked: Boolean) {
        detailViewModel.secretButtonClickedFalse()
        if (isClicked) {
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            if (!detailViewModel.getReplyFlag()) {
                binding.imgDetailSecret.isEnabled = false
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_on)
            }
            binding.txtDetailMessage.text = "${detailViewModel.getId()} 님에게 답글을 남기는 중"
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.addReplyChildclickedFalse()
                binding.imgDetailSecret.isEnabled = true
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_off)
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
        binding.edttextDetailContent.requestFocus()
    }

    private fun changeChildclickListener(isChildChangeClicked: Boolean) {
        detailViewModel.secretButtonClickedFalse()
        if (isChildChangeClicked) {
            if (!detailViewModel.getReplyChildFlag()) {
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_on)
            }
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            binding.imgDetailSecret.isEnabled = false
            binding.txtDetailMessage.text = "댓글을 수정중입니다"
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.changeChildClickedFalse()
                binding.imgDetailSecret.isEnabled = true
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_off)
                detailViewModel.answerText.value = ""
            }
            focusKeyboard()
        }
    }

    private fun changeClickListener(isChangeClicked: Boolean) {
        if (isChangeClicked) {
            detailViewModel.secretButtonClickedFalse()
            if (!detailViewModel.getReplyParentFlag()) {
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_on)
            }
            binding.constraintDetailSnakbar.visibility = View.VISIBLE
            binding.txtDetailMessage.text = "댓글을 수정중입니다"
            binding.imgDetailSecret.isEnabled = false
            binding.btnDetailCancel.setOnClickListener {
                binding.constraintDetailSnakbar.visibility = View.GONE
                detailViewModel.changeClickedFalse()
                binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_off)
                binding.imgDetailSecret.isEnabled = true
                detailViewModel.answerText.value = ""
            }
            focusKeyboard()
        }
    }

    private fun secretClickListener(isSecretClicked: Boolean) {
        if (isSecretClicked) {
            binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_on)
        } else {
            binding.imgDetailSecret.setImageResource(R.drawable.ic_secret_off)
        }
    }
}