package com.teambeme.beme.reply.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyChildBinding
import com.teambeme.beme.reply.model.ReplyData

class ReplyChildAdapter() :
    ListAdapter<ReplyData, ReplyChildAdapter.ReplyChildViewHolder>(
        ReplyChildDiffCallback
    ) {

    class ReplyChildViewHolder(private val binding: ReplyChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReplyData) {
            binding.replyData = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyChildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReplyChildBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.reply_child,
            parent,
            false
        )
        return ReplyChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyChildViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun addItem(Data: MutableList<ReplyData>) {
        submitList(Data)
    }
}