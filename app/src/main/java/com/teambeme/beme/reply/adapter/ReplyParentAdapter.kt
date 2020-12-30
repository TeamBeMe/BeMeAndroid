package com.teambeme.beme.reply.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyParentBinding
import com.teambeme.beme.reply.model.ReplyData
import com.teambeme.beme.reply.model.ReplyParentData

class ReplyParentAdapter :
    ListAdapter<ReplyParentData, ReplyParentAdapter.ReplyParentViewHolder>(
        ReplyDiffUtillCallback
    ) {

    inner class ReplyParentViewHolder(private val binding: ReplyParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReplyParentData) {
            binding.txtReplyparentId.text = data.txt_id
            binding.txtReplyparentComment.text=data.txt_comment
            binding.txtReplyparentTime.text=data.txt_time
            binding.rcvReplyparentChild.adapter=ReplyChildAdapter()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyParentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReplyParentBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.reply_parent,
            parent,
            false
        )
        return ReplyParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyParentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}