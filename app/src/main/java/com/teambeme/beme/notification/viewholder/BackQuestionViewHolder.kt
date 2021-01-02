package com.teambeme.beme.notification.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.notification.model.BackQuestionData

class BackQuestionViewHolder (private val binding: ItemThisWeekBackQuestionBinding) :
        RecyclerView.ViewHolder(binding.root){
    fun bind(backQuestion : BackQuestionData) {
        with(binding){
            backQuestionData = backQuestion
            executePendingBindings()
        }
    }
}