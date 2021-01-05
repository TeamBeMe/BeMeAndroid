package com.teambeme.beme.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.notification.model.BackQuestionData

class BackQuestionAdapter:
    ListAdapter<BackQuestionData, BackQuestionAdapter.BackQuestionViewHolder>(
        BackQuestionDiffUtil()
) {
    inner class BackQuestionViewHolder(private val binding: ItemThisWeekBackQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(backQuestionData: BackQuestionData) {
            binding.backQuestion = backQuestionData
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BackQuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemThisWeekBackQuestionBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_this_week_back_question,
                parent,
                false
            )
        return BackQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackQuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class BackQuestionDiffUtil : DiffUtil.ItemCallback<BackQuestionData>() {
        override fun areItemsTheSame(
            oldItem: BackQuestionData,
            newItem: BackQuestionData
        ) =
            (oldItem.back_question == newItem.back_question)

        override fun areContentsTheSame(
            oldItem: BackQuestionData,
            newItem: BackQuestionData
        ) =
            (oldItem == newItem)
    }
}
