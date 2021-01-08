package com.teambeme.beme.notification

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.notification.adapter.BackQuestionAdapter
import com.teambeme.beme.notification.adapter.RecentActivitiesAdapter
import com.teambeme.beme.notification.viewmodel.NotificationViewModel

class NotificationActivity : BindingActivity<ActivityNotificationBinding>(R.layout.activity_notification) {
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding(binding)

        setBackQuestionAdapter(binding)
        notificationViewModel.setDummyBackQuestionNotification()

        setRecentActivitiesAdapter(binding)
        notificationViewModel.setDummyRecentNotification()
        setClickListenerForPlusRecentActivitiesData(binding)
        setObserveRecentActivities(binding)

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
    }

    private fun initBinding(binding: ActivityNotificationBinding) {
        binding.apply {
            notificationViewModel = notificationViewModel
            lifecycleOwner = this@NotificationActivity
        }
    }

    private fun setBackQuestionAdapter(binding: ActivityNotificationBinding) {
        val backQuestionAdapter = BackQuestionAdapter()
        binding.rcvThisWeekBackQuestion.apply {
            adapter = backQuestionAdapter
            layoutManager = LinearLayoutManager(this@NotificationActivity)
        }
        notificationViewModel.backQuestionList.observe(this) { list ->
            backQuestionAdapter.replaceQuestionList(list)
        }
    }

    private fun setRecentActivitiesAdapter(binding: ActivityNotificationBinding) {
        val recentActivitiesAdapter = RecentActivitiesAdapter()
        binding.rcvRecentActivities.adapter = recentActivitiesAdapter
    }

    private fun setClickListenerForPlusRecentActivitiesData(binding: ActivityNotificationBinding) {
        binding.btnRecentActivitiesShowMore.setOnClickListener {
            notificationViewModel.addDummyRecentNotification()
        }
    }

    private fun setObserveRecentActivities(binding: ActivityNotificationBinding) {
        notificationViewModel.recentActivitiesList.observe(this) { recentActivitiesList ->
            recentActivitiesList?.let {
                if (binding.rcvRecentActivities.adapter != null) with(binding.rcvRecentActivities.adapter as RecentActivitiesAdapter) {
                    submitList(recentActivitiesList)
                }
            }
        }
    }

    companion object {
        const val TAG = "NotificationActivity"
    }
}
