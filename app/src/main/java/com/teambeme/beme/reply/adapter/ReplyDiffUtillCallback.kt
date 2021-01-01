package com.teambeme.beme.reply.adapter

import androidx.recyclerview.widget.DiffUtil
import com.teambeme.beme.reply.model.ReplyParentData

object ReplyDiffUtillCallback : DiffUtil.ItemCallback<ReplyParentData>() {
    override fun areItemsTheSame(
        oldItem: ReplyParentData,
        newItem: ReplyParentData
    ): Boolean {
        return oldItem.txt_comment == newItem.txt_comment
    }

    override fun areContentsTheSame(
        oldItem: ReplyParentData,
        newItem: ReplyParentData
    ): Boolean {
        return oldItem == newItem
    }

}