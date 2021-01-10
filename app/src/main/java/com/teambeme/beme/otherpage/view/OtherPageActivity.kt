package com.teambeme.beme.otherpage.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.otherPageViewModel = otherViewModel
        binding.lifecycleOwner = this
        val otherAdapter = OtherPageAdapter()
        setAdapter(otherAdapter)
        otherViewModel.requestItem()
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
            otherViewModel.addDummyAnswer()
        }
    }
}