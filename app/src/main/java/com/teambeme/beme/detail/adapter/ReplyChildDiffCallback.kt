package com.teambeme.beme.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.teambeme.beme.detail.model.ReplyData

object ReplyChildDiffCallback : DiffUtil.ItemCallback<ReplyData>() {
    override fun areItemsTheSame(
        oldItem: ReplyData,
        newItem: ReplyData
    ): Boolean {
        return oldItem.txt_comment == newItem.txt_comment
    }

    override fun areContentsTheSame(
        oldItem: ReplyData,
        newItem: ReplyData
    ): Boolean {
        return oldItem == newItem
    }
}