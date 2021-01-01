package com.teambeme.beme.notification

import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding

class BackDataViewHolder (private val binding: ItemThisWeekBackQuestionBinding) :
        RecyclerView.ViewHolder(binding.root){
    fun bind(backQuestion : BackQuestionData) {
        with(binding){
            backQuestionData = backQuestion
            executePendingBindings()
        }
    }
}