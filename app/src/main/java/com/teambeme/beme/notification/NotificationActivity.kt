package com.teambeme.beme.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.notification.adapter.BackQuestionAdapter
import com.teambeme.beme.notification.adapter.RecentActivitiesAdapter
import com.teambeme.beme.notification.viewmodel.NotificationViewModel

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)
        binding.notificationVM = notificationViewModel
        binding.lifecycleOwner = this

        notificationViewModel.setDummyRecentNotification()
        notificationViewModel.setDummyBackQuestionNotification()
        setAdapter(binding)
        setClickListenerForPlusData(binding)
        setObserve(binding)
    }

    private fun setAdapter(binding: ActivityNotificationBinding) {
        val backQuestionAdapter = BackQuestionAdapter()
        val recentActivitiesAdapter = RecentActivitiesAdapter()
        binding.rcvThisWeekBackQuestion.adapter = backQuestionAdapter
        binding.rcvRecentActivities.adapter = recentActivitiesAdapter
    }


    private fun setClickListenerForPlusData(binding: ActivityNotificationBinding) {
        binding.btnRecentActivitiesShowMore.setOnClickListener {
            notificationViewModel.addDummyRecentNotification()
        }
    }

    private fun setObserve(binding: ActivityNotificationBinding) {
        notificationViewModel.recentActivitiesList.observe(this) { recentActivitiesList ->
            recentActivitiesList?.let {
                if (binding.rcvRecentActivities.adapter != null) with(binding.rcvRecentActivities.adapter as RecentActivitiesAdapter) {
                    submitList(recentActivitiesList)
                }
            }
        }
        notificationViewModel.backQuestionList.observe(this) { backQuestionList ->
            backQuestionList?.let {
                if (binding.rcvThisWeekBackQuestion.adapter != null) with(binding.rcvThisWeekBackQuestion.adapter as BackQuestionAdapter) {
                    submitList(backQuestionList)
                }
            }
        }
    }


}

