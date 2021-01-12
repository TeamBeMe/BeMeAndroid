package com.teambeme.beme.notification.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
        })

        binding.btnBackNotice.setOnClickListener {
            finish()
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
