package com.teambeme.beme.notification

import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding

class RecentActivitiesViewHolder (private val binding : ItemRecentActivitiesBinding):
        RecyclerView.ViewHolder(binding.root){
    fun bind(recentActivities : RecentActivitiesData){
        with(binding){
            recentActivitiesData = recentActivities
            executePendingBindings()
        }
    }
}