package com.teambeme.beme.notification.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.NoticeDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.notification.adapter.NoticeAdapter
import com.teambeme.beme.notification.repository.NoticeRepositoryImpl
import com.teambeme.beme.notification.viewmodel.NoticeViewModel
import com.teambeme.beme.notification.viewmodel.NoticeViewModelFactory

class NotificationActivity : BindingActivity<ActivityNotificationBinding>(R.layout.activity_notification) {
    private val noticeViewModelFactory =
        NoticeViewModelFactory(NoticeRepositoryImpl(NoticeDataSourceImpl(RetrofitObjects.getNoticeService())))

    private val noticeViewModel: NoticeViewModel by viewModels { noticeViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding(binding)

        val noticeAdapter = NoticeAdapter()
        binding.rcvRecentActivities.adapter = noticeAdapter

        noticeViewModel.requestNoticeItem()
        noticeViewModel.requestAddNoticeItem()

        noticeViewModel.noticeDataList.observe(this) { it ->
            it.let { noticeAdapter.submitList(it) }
        }
        noticeViewModel.isMax.observe(this) {
            isMaxListener(it)
        }

        setClickListenerForPlusData(binding)
        binding.btnBackNotice.setOnClickListener { finish() }

        binding.btnBackNotice.setOnClickListener { finish()
        }
    }

    private fun initBinding(binding: ActivityNotificationBinding) {
        binding.noticeViewModel = noticeViewModel
        binding.lifecycleOwner = this
    }

    private fun isMaxListener(isMax: Boolean) {
        if (isMax) {
            binding.btnRecentActivitiesShowMore.visibility = View.GONE
        } else {
            binding.btnRecentActivitiesShowMore.visibility = View.VISIBLE
        }
    }

    private fun setClickListenerForPlusData(
        binding: ActivityNotificationBinding
    ) {
        binding.btnRecentActivitiesShowMore.setOnClickListener {
            noticeViewModel.requestAddNoticeItem()
        }
    }

    companion object {
        const val TAG = "NotificationActivity"
    }
}
