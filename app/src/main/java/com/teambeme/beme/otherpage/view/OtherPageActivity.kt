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

class OtherPageActivity : BindingActivity<ActivityOtherPageBinding>(R.layout.activity_other_page) {
    private val otherViewModelFactory =
        OtherPageViewModelFactory(OtherPageRepositoryImpl(OtherPageDataSourceImpl(RetrofitObjects.getOtherPageService())))
    private val otherViewModel: OtherPageViewModel by viewModels { otherViewModelFactory }
    private var userId = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.otherPageViewModel = otherViewModel
        binding.lifecycleOwner = this
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
    }

    private fun scrapListener() {
        otherViewModel.putScrap()
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