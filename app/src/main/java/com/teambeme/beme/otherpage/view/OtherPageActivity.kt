package com.teambeme.beme.otherpage.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityOtherPageBinding
import com.teambeme.beme.detail.view.BottomOtherReplyFragment
import com.teambeme.beme.otherpage.adapter.OtherPageAdapter
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModel

class OtherPageActivity : BindingActivity<ActivityOtherPageBinding>(R.layout.activity_other_page) {
    private val otherViewModel: OtherPageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.otherPageViewModel = OtherPageViewModel()
        binding.lifecycleOwner = this
        val otherAdapter = OtherPageAdapter()
        setAdapter(otherAdapter)
        otherViewModel.setDummyOtherAnswer()
        otherViewModel.otherAnswerList.observe(this) { it ->
            it.let { otherAdapter.submitList(it) }
        }
        setClickListenerForPlusData(binding, otherAdapter)
        binding.btnOtherpageBack.setOnClickListener { finish() }
        binding.btnOtherpageDot3.setOnClickListener { dotClickListener() }
    }

    private fun setAdapter(otherAdapter: OtherPageAdapter) {
        binding.rcvOtherdata.apply {
            layoutManager = LinearLayoutManager(this@OtherPageActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lastVisiblePosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val itemTotalCount = adapter!!.itemCount - 1
                    if (lastVisiblePosition == itemTotalCount) {
                        binding.btnOtherShowmore.visibility = View.VISIBLE
                    } else {
                        binding.btnOtherShowmore.visibility = View.GONE
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
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
            binding.btnOtherShowmore.visibility = View.GONE
            otherViewModel.addDummyAnswer()
            otherAdapter.submitList(otherViewModel.otherAnswerList.value?.toMutableList())
        }
    }
}