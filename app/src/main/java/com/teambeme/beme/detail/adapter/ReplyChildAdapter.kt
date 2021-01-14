package com.teambeme.beme.detail.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyChildBinding
import com.teambeme.beme.detail.model.ResponseDetail
import com.teambeme.beme.detail.viewmodel.DetailViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity

class ReplyChildAdapter(
    private val context: Context,
    private val parentPosition: Int,
    private val viewModel: DetailViewModel
) :
    RecyclerView.Adapter<ReplyChildAdapter.ReplyChildViewHolder>() {
    var children = mutableListOf<ResponseDetail.Data.Comment.Children>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReplyChildAdapter.ReplyChildViewHolder {
        Log.d("test", "dsdd")
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

        fun onBind(children: ResponseDetail.Data.Comment.Children) {
            binding.replyData = children
        }

        val dot: ImageView = binding.imgReplychildDot3
        val profile: ImageView = binding.imgReplychildProfile
    }

    override fun getItemCount(): Int = children.size

    override fun onBindViewHolder(holder: ReplyChildAdapter.ReplyChildViewHolder, position: Int) {
        holder.onBind(children[position])

        holder.onBind(children[position]).let {
            with(holder) {
                dot.setOnClickListener {
                    viewModel.setChildPosition(parentPosition, adapterPosition)
                }
                profile.setOnClickListener { view ->
                    val intent = Intent(view.context, OtherPageActivity::class.java)
                    intent.putExtra("userId", children[position].userId)
                    if (children[position].isAuthor)
                        intent.putExtra("isAuthor", true)
                    view.context.startActivity(intent)
                }
            }
        }
    }

    fun setListItems(children: List<ResponseDetail.Data.Comment.Children>) {
        this.children = children.toMutableList()
        notifyDataSetChanged()
    }
}