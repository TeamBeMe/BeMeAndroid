package com.teambeme.beme.notification.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding
import com.teambeme.beme.notification.NotificationActivity
import com.teambeme.beme.notification.model.RecentActivitiesData


class RecentActivitiesAdapter :
    ListAdapter<RecentActivitiesData, RecentActivitiesAdapter.RecentActivitiesViewHolder>(
        RecentActivitiesDiffUtil()
    ) {

    inner class RecentActivitiesViewHolder(private val binding: ItemRecentActivitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recentActivitiesData: RecentActivitiesData) {
            binding.recentActivities = recentActivitiesData

//            Glide
//                .with(itemView)
//                .load(recentActivitiesData.userPicUrl)
//                .placeholder(R.drawable.ic_default_profile_pic)
//                .error(R.drawable.ic_default_profile_pic).into(binding.notificationProfilePic)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentActivitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentActivitiesBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_recent_activities,
                parent,
                false
            )

        return RecentActivitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentActivitiesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class RecentActivitiesDiffUtil : DiffUtil.ItemCallback<RecentActivitiesData>() {
        override fun areItemsTheSame(
            oldItem: RecentActivitiesData,
            newItem: RecentActivitiesData
        ) =
            (oldItem.notifi_message == newItem.notifi_message)

        override fun areContentsTheSame(
            oldItem: RecentActivitiesData,
            newItem: RecentActivitiesData
        ) =
            (oldItem == newItem)
    }

}

