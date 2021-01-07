package com.teambeme.beme.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyChildBinding
import com.teambeme.beme.detail.model.ReplyData
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class ReplyChildAdapter(
    private val context: Context,
    private val par: Int,
    private val viewModel: DetailViewModel
) :
    RecyclerView.Adapter<ReplyChildAdapter.ReplyChildViewHolder>() {
    var data = mutableListOf<ReplyData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReplyChildAdapter.ReplyChildViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReplyChildBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.reply_child,
            parent,
            false
        )
        return ReplyChildViewHolder(binding)
    }

    inner class ReplyChildViewHolder(private val binding: ReplyChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ReplyData) {
            binding.replyData = data
        }

        val dot: ImageView = binding.imgReplychildDot3
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ReplyChildAdapter.ReplyChildViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.onBind(data[position]).let {
            with(holder) {
                dot.setOnClickListener {
                    viewModel.setChildPosition(par, adapterPosition)
                }
            }
        }
    }

    fun setListItems(data: List<ReplyData>) {
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }
}