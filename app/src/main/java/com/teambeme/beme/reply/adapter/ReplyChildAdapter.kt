package com.teambeme.beme.reply.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyParentBinding
import com.teambeme.beme.reply.model.ReplyData
import com.teambeme.beme.reply.model.ReplyParentData

class ReplyChildAdapter :
    ListAdapter<ReplyData, ReplyChildAdapter.ReplyChildViewHolder>(
        ReplyChildDiffCallback
    ) {

    inner class ReplyChildViewHolder(private val binding: ReplyParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReplyData) {
            binding.txtReplyparentId.text = data.txt_id
            binding.txtReplyparentComment.text=data.txt_comment
            binding.txtReplyparentTime.text=data.txt_time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyChildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReplyParentBinding = DataBindingUtil.inflate(
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

}