package com.teambeme.beme.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotificationBinding
    private lateinit var backQuestionAdapter: BackQuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notification)
        binding.lifecycleOwner = this

        backQuestionAdapter= BackQuestionAdapter(this)

        binding.rcvThisWeekBackQuestion.adapter = backQuestionAdapter
        binding.rcvThisWeekBackQuestion.layoutManager = LinearLayoutManager(this)

        backQuestionAdapter.backQuestions = mutableListOf(
            //dummy data
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?1"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?2"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?3"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?4"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?5"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?6"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?7"),
            BackQuestionData("가장 좋아하는 음식은 무엇인가요?8")

        )

        backQuestionAdapter.notifyDataSetChanged()


    }
}