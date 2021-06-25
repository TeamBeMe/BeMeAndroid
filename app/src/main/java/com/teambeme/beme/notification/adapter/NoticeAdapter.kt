package com.teambeme.beme.notification.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.notification.model.ResponseNoticeData
import com.teambeme.beme.otherpage.view.OtherPageActivity
import com.teambeme.beme.util.recordClickEvent

class NoticeAdapter :
    ListAdapter<ResponseNoticeData.Data.Activity, NoticeAdapter.NoticeViewHolder>(NoticeDiffUtil()) {

    class NoticeViewHolder(private val binding: ItemRecentActivitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activities: ResponseNoticeData.Data.Activity) {
            binding.recentActivities = activities
            Log.d("Network is success_2", activities.toString())
        }

        val userProfilePic = binding.notificationProfilePic
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentActivitiesBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recent_activities, parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.bind(getItem(position)).let {
            with(holder) {
                userProfilePic.setOnClickListener { view ->
                    val intent = Intent(view.context, OtherPageActivity::class.java)
                    intent.putExtra("userId", getItem(position).userId)
                    Log.d("Internt", position.toString())
                    Log.d("Internt", getItem(position).userId.toString())
                    view.context.startActivity(intent)
                }
                itemView.setOnClickListener { view ->
                    recordClickEvent("BUTTON", "CLCIK_ALARM")
                    if (getItem(position).questionTitle != null) {
                        val intent = Intent(view.context, DetailActivity::class.java)
                        intent.putExtra("answerId", getItem(holder.adapterPosition).answerId)
                        view.context.startActivity(intent)
                    } else {
                        val intent = Intent(view.context, OtherPageActivity::class.java)
                        intent.putExtra("userId", getItem(holder.adapterPosition).userId)
                        view.context.startActivity(intent)
                    }
                }
            }
        }
    }

    private class NoticeDiffUtil : DiffUtil.ItemCallback<ResponseNoticeData.Data.Activity>() {
        override fun areItemsTheSame(
            oldItem: ResponseNoticeData.Data.Activity,
            newItem: ResponseNoticeData.Data.Activity
        ) =
            (oldItem.userId == newItem.userId) && (oldItem.questionTitle == newItem.questionTitle) && (oldItem.type == newItem.type)

        override fun areContentsTheSame(
            oldItem: ResponseNoticeData.Data.Activity,
            newItem: ResponseNoticeData.Data.Activity
        ) =
            (oldItem == newItem)
    }
}
