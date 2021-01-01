package com.teambeme.beme.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var backQuestionAdapter: BackQuestionAdapter
    private lateinit var recentActivitiesAdapter: RecentActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)
        binding.lifecycleOwner = this

        backQuestionAdapter = BackQuestionAdapter(this)
        recentActivitiesAdapter = RecentActivitiesAdapter(this)

        binding.rcvThisWeekBackQuestion.adapter = backQuestionAdapter
        binding.rcvThisWeekBackQuestion.layoutManager = LinearLayoutManager(this)

        binding.rcvRecentActivities.adapter = recentActivitiesAdapter
        binding.rcvRecentActivities.layoutManager = LinearLayoutManager(this)

        backQuestionAdapter.backQuestions = mutableListOf(
            //dummy data
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?1가장 좋아하는 음식은 무엇인가요?1가장 좋아하는 음식은 무엇인가요?1"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?2가장 좋아하는 음식은 무엇인가요?2"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?3"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?4"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?5"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?6"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?7"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?8")

        )

        recentActivitiesAdapter.recentActivitiesList = mutableListOf(
            //dummy data
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.1"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.2"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.3"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.4"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.5"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.6"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.7"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.8"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.9"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.10"),
            RecentActivitiesData("aaa님이 \"이번주를 후회없이 보내는 방법\"에 대한 나의 글에 댓글을 달았습니다.11")
            )



        backQuestionAdapter.notifyDataSetChanged()
        recentActivitiesAdapter.notifyDataSetChanged()

    }
}