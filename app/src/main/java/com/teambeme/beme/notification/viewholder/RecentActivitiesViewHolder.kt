package com.teambeme.beme.notification.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding
import com.teambeme.beme.notification.model.RecentActivitiesData

class RecentActivitiesViewHolder (private val binding : ItemRecentActivitiesBinding):
        RecyclerView.ViewHolder(binding.root){
    fun bind(recentActivities : RecentActivitiesData){
        with(binding){
            recentActivitiesData = recentActivities
            executePendingBindings()
        }
    }
}