package com.teambeme.beme.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentActivitiesBinding
import com.teambeme.beme.notification.model.BackQuestionData
import com.teambeme.beme.notification.model.RecentActivitiesData

class RecentActivitiesAdapter :
    ListAdapter<RecentActivitiesData, RecentActivitiesAdapter.RecentActivitiesViewHolder>(
        RecentActivitiesDiffUtil()
    ) {

//    var recentActivitiesDataList : ArrayList<RecentActivitiesData> = arrayListOf()

    class RecentActivitiesViewHolder(private val binding: ItemRecentActivitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recentActivitiesData: RecentActivitiesData) {
            binding.recentActivities = recentActivitiesData
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

//        var view = holder.itemView

//        when(recentActivitiesDataList[position].kind){
//            1-> {
//                val str_0 = recentActivitiesDataList[position].userID + "님이 나의 글에 댓글을 남겼습니다."
//                view.notification_message.text=str_0
//
//            }
//            2-> {
//                val str_0 = recentActivitiesDataList[position].userID + "님이 답글을 남겼습니다."
//                view.notification_message.text=str_0
//
//            }
//            3-> {
//                val str_0 = recentActivitiesDataList[position].userID + "님이 나를 팔로우합니다."
//                view.notification_message.text=str_0
//
//            }
//        }
    }

    private class RecentActivitiesDiffUtil : DiffUtil.ItemCallback<RecentActivitiesData>() {
        override fun areItemsTheSame(
            oldItem: RecentActivitiesData,
            newItem: RecentActivitiesData
        ) = oldItem.notifiMessage == newItem.notifiMessage

        override fun areContentsTheSame(
            oldItem: RecentActivitiesData,
            newItem: RecentActivitiesData
        ) = oldItem == newItem
    }
}