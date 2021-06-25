package com.teambeme.beme.notification.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.notification.adapter.NoticeAdapter
import com.teambeme.beme.notification.viewmodel.NoticeViewModel
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationActivity :
    BindingActivity<ActivityNotificationBinding>(R.layout.activity_notification) {
    private val noticeViewModel: NoticeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, Color.WHITE)

        initBinding(binding)

        val noticeAdapter = NoticeAdapter()
        binding.rcvRecentActivities.adapter = noticeAdapter

        noticeViewModel.requestNoticeItem()

        noticeViewModel.noticeDataList.observe(this) { it ->
            it.let { noticeAdapter.submitList(it) }
        }

        setIsMoreDataObserve()
        setClickListenerForPlusData(binding)
        binding.btnBackNotice.setOnClickListener { finish() }
    }

    private fun setIsMoreDataObserve() {
        noticeViewModel.isMorePage.observe(this) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnRecentActivitiesShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnRecentActivitiesShowMore.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun initBinding(binding: ActivityNotificationBinding) {
        binding.noticeViewModel = noticeViewModel
        binding.lifecycleOwner = this
    }

    private fun setClickListenerForPlusData(
        binding: ActivityNotificationBinding
    ) {
        binding.btnRecentActivitiesShowMore.setOnClickListener {
            noticeViewModel.requestAddNoticeItem()
        }
    }
}
