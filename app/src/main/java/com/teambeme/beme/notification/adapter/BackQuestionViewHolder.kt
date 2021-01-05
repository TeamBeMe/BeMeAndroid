package com.teambeme.beme.notification.adapter

import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.notification.model.BackQuestionData

class BackQuestionViewHolder(private val binding: ItemThisWeekBackQuestionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val deleteBtn = binding.btnDeleteBackQuestion

    fun bind(item: BackQuestionData) {
        with(binding) {
            backQuestion = item
            executePendingBindings()
        }

    }

}