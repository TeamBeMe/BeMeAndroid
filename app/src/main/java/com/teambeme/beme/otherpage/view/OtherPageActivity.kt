package com.teambeme.beme.otherpage.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.OtherPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityOtherPageBinding
import com.teambeme.beme.detail.view.BottomOtherReplyFragment
import com.teambeme.beme.otherpage.adapter.OtherPageAdapter
import com.teambeme.beme.otherpage.repository.OtherPageRepositoryImpl
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModel
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModelFactory
import com.teambeme.beme.util.StatusBarUtil

class OtherPageActivity : BindingActivity<ActivityOtherPageBinding>(R.layout.activity_other_page) {
    private val otherViewModelFactory =
        OtherPageViewModelFactory(OtherPageRepositoryImpl(OtherPageDataSourceImpl(RetrofitObjects.getOtherPageService())))
    private val otherViewModel: OtherPageViewModel by viewModels { otherViewModelFactory }
    private var userId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        binding.otherPageViewModel = otherViewModel
        binding.lifecycleOwner = this
        userId = intent.getIntExtra("userId", 0)
        val isAuthor = intent.getBooleanExtra("isAuthor", false)
        myProfileListener(isAuthor)
        val otherAdapter = OtherPageAdapter(otherViewModel)
        setAdapter(otherAdapter)
        otherViewModel.requestUser(userId)
        otherViewModel.requestItem(userId)
        otherViewModel.otherAnswerList.observe(this) { it ->
            it.let { otherAdapter.submitList(it) }
        }
        otherViewModel.isMax.observe(this) { it ->
            isMaxListener(it)
        }
        setClickListenerForPlusData(binding, otherAdapter)
        binding.btnOtherpageBack.setOnClickListener { finish() }
        binding.btnOtherpageDot3.setOnClickListener { dotClickListener() }
        otherViewModel.scrapPosition.observe(this) {
            scrapListener()
        }
        otherViewModel.isOtherEmpty.observe(this) {
            isEmptyListener(it)
        }
    }

    private fun myProfileListener(isAuthor: Boolean) {
        if (isAuthor) {
            binding.btnOtherpageDot3.visibility = View.GONE
            binding.chipFollow.visibility = View.GONE
        }
    }

    private fun scrapListener() {
        otherViewModel.putScrap()
    }

    private fun isEmptyListener(isEmpty: Boolean) {
        if (isEmpty) {
            binding.rcvOtherdata.visibility = View.GONE
            binding.constraintOtherEmpty.visibility = View.VISIBLE
        } else {
            binding.constraintOtherEmpty.visibility = View.GONE
            binding.rcvOtherdata.visibility = View.VISIBLE
        }
    }

    private fun isMaxListener(isMax: Boolean) {
        if (isMax) {
            binding.btnOtherShowmore.visibility = View.GONE
        } else {
            binding.btnOtherShowmore.visibility = View.VISIBLE
        }
    }

    private fun setAdapter(otherAdapter: OtherPageAdapter) {
        binding.rcvOtherdata.apply {
            layoutManager = LinearLayoutManager(this@OtherPageActivity)
            adapter = otherAdapter
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
        binding: ActivityOtherPageBinding,
        otherAdapter: OtherPageAdapter
    ) {
        binding.btnOtherShowmore.setOnClickListener {
            otherViewModel.requestAddItem(userId)
        }
    }
}