package com.teambeme.beme.notification.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding
import com.teambeme.beme.notification.model.ResponseNoticeData

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

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentActivitiesBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recent_activities, parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))

//        holder.userProfilePic.setOnClickListener {
//
//        }
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