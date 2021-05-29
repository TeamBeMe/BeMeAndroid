package com.teambeme.beme.otherpage.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityOtherPageBinding
import com.teambeme.beme.detail.view.BottomOtherReplyFragment
import com.teambeme.beme.otherpage.adapter.OtherPageAdapter
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModel
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherPageActivity : BindingActivity<ActivityOtherPageBinding>(R.layout.activity_other_page) {
    private val otherViewModel: OtherPageViewModel by viewModels()
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        binding.otherPageViewModel = otherViewModel
        binding.lifecycleOwner = this
        userId = intent.getIntExtra("userId", 0)
        val isAuthor = intent.getBooleanExtra("isAuthor", false)
        myProfileListener(isAuthor)
        otherViewModel.requestUser(userId)
        otherViewModel.requestItem(userId)
        setRcvOtherDataAdapter()
        setOtherDataObserve()
        setIsMaxObserve()
        setClickListenerForPlusData(binding)
        setOnClickListenerForGoBack()
        setOnClickListenerForDot3()
        setIsEmptyObserve()
    }

    private fun setOnClickListenerForGoBack() {
        binding.btnOtherpageBack.setOnClickListener { finish() }
    }

    private fun setOnClickListenerForDot3() {
        binding.btnOtherpageDot3.setOnClickListener { dotClickListener() }
    }

    private fun myProfileListener(isAuthor: Boolean) {
        if (isAuthor) {
            binding.btnOtherpageDot3.visibility = View.GONE
            binding.chipFollow.visibility = View.GONE
        }
    }

    private fun setIsEmptyObserve() {
        otherViewModel.isOtherEmpty.observe(this) { isOtherEmpty ->
            isOtherEmpty.let {
                if (isOtherEmpty) {
                    binding.rcvOtherdata.visibility = View.GONE
                    binding.constraintOtherEmpty.visibility = View.VISIBLE
                } else {
                    binding.constraintOtherEmpty.visibility = View.GONE
                    binding.rcvOtherdata.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setIsMaxObserve() {
        otherViewModel.isMax.observe(this) { isMax ->
            isMax.let {
                if (isMax) {
                    binding.btnOtherShowmore.visibility = View.GONE
                } else {
                    binding.btnOtherShowmore.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setRcvOtherDataAdapter() {
        val otherAdapter = OtherPageAdapter(otherViewModel, this)
        binding.rcvOtherdata.apply {
            layoutManager = LinearLayoutManager(this@OtherPageActivity)
            adapter = otherAdapter
        }
    }

    private fun setOtherDataObserve() {
        otherViewModel.otherAnswerList.observe(this) { otherAnswerList ->
            otherAnswerList.let {
                if (binding.rcvOtherdata.adapter != null) with(binding.rcvOtherdata.adapter as OtherPageAdapter) {
                    submitList(otherAnswerList)
                }
            }
        }
    }

    private fun dotClickListener() {
        val bottomSheetFragment = BottomOtherReplyFragment()
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }

    private fun setClickListenerForPlusData(
        binding: ActivityOtherPageBinding
    ) {
        binding.btnOtherShowmore.setOnClickListener {
            otherViewModel.requestAddItem(userId)
        }
    }
}
