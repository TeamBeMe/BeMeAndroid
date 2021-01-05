package com.teambeme.beme.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.notification.adapter.BackQuestionAdapter
import com.teambeme.beme.notification.adapter.RecentActivitiesAdapter
import com.teambeme.beme.notification.model.BackQuestionData
import com.teambeme.beme.notification.viewmodel.NotificationViewModel

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var backQuestionAdapter: BackQuestionAdapter
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)
        binding.notificationVM = notificationViewModel
        binding.lifecycleOwner = this

        backQuestionAdapter = BackQuestionAdapter(this)
        binding.rcvThisWeekBackQuestion.adapter = backQuestionAdapter
        binding.rcvThisWeekBackQuestion.layoutManager = LinearLayoutManager(this)

        backQuestionAdapter.datas = mutableListOf(
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?1가장 좋아하는 음식은 무엇인가요?1가장 좋아하는 음식은 무엇인가요?1"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?2가장 좋아하는 음식은 무엇인가요?2"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?3"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?4"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?5"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?6"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?7"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?8")
        )

        notificationViewModel.setDummyRecentNotification()

        setAdapter(binding)
        setClickListenerForPlusData(binding)
        setObserve(binding)

        backQuestionAdapter.notifyDataSetChanged()
    }

    private fun setAdapter(binding: ActivityNotificationBinding) {
        val recentActivitiesAdapter = RecentActivitiesAdapter()
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

    }


}

