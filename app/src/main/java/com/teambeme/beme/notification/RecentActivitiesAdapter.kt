package com.teambeme.beme.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ActivityNotificationBinding
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding


class RecentActivitiesAdapter (private val context: Context) :
    RecyclerView.Adapter<RecentActivitiesViewHolder>(){
    private lateinit var binding : ItemRecentActivitiesBinding

    var recentActivitiesList = mutableListOf<RecentActivitiesData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentActivitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentActivitiesBinding.inflate(inflater, parent, false)
        return RecentActivitiesViewHolder(binding)
    }

    override fun getItemCount(): Int = recentActivitiesList.size

    override fun onBindViewHolder(holder:  RecentActivitiesViewHolder, position: Int) {
        holder.bind(recentActivitiesList[position])
    }


}