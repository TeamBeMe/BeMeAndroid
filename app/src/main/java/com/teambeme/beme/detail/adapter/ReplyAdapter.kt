package com.teambeme.beme.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyParentBinding
import com.teambeme.beme.detail.model.ReplyParentData
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class ReplyAdapter(private val context: Context, private val viewModel: DetailViewModel) :
    RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {
    var data = mutableListOf<ReplyParentData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReplyAdapter.ReplyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ReplyParentBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.reply_parent,
            parent,
            false
        )
        return ReplyViewHolder(binding)
    }

    inner class ReplyViewHolder(private val binding: ReplyParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ReplyParentData) {
            binding.replyParentData = data
            if (data.data_child[0].txt_id != "") {
                val replyAdapter = ReplyChildAdapter(context, adapterPosition, viewModel)
                binding.rcvReplyparentChild.adapter = replyAdapter
                binding.rcvReplyparentChild.layoutManager = LinearLayoutManager(context)
                replyAdapter.setListItems(data.data_child)
            } else {
                binding.rcvReplyparentChild.visibility = View.GONE
            }
        }

        val open_btn: TextView = binding.txtReplyparentOpen
        val child_rcv: RecyclerView = binding.rcvReplyparentChild
        val dot: ImageView = binding.imgReplyparentDot3
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.onBind(data[position]).let {
            with(holder) {
                open_btn.setOnClickListener {
                    if (open_btn.text == "답글 보기") {
                        child_rcv.visibility = View.VISIBLE
                        open_btn.text = "답글 접기"
                    } else {
                        child_rcv.visibility = View.GONE
                        open_btn.text = "답글 보기"
                    }
                }
                if (data[position].data_child[0].txt_id == "") {
                    open_btn.visibility = View.GONE
                } else {
                    open_btn.visibility = View.VISIBLE
                }
                dot.setOnClickListener {
                    viewModel.setPosition(position)
                }
                child_rcv.setOnClickListener {
                    viewModel.setPosition(position)
                }
            }
        }
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun replaceReplyList(list: List<ReplyParentData>) {
        data = list.toMutableList()
        notifyDataSetChanged()
    }
}