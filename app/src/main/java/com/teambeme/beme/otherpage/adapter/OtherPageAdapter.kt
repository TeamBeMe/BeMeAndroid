package com.teambeme.beme.otherpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemOtherPageBinding
import com.teambeme.beme.otherpage.model.Answer

class OtherPageAdapter :
    ListAdapter<Answer, OtherPageAdapter.OtherPageViewHolder>(OtherPageDiffUtil()) {

    class OtherPageViewHolder(private val binding: ItemOtherPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) {
            binding.answer = answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherPageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemOtherPageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_other_page, parent, false)
        return OtherPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherPageDiffUtil : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem.answerIdx == newItem.answerIdx)

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem == newItem)
    }
}