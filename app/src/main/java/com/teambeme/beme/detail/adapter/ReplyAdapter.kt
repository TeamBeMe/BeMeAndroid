package com.teambeme.beme.detail.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.teambeme.beme.detail.model.ResponseDetail
import com.teambeme.beme.detail.viewmodel.DetailViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity

class ReplyAdapter(private val context: Context, private val viewModel: DetailViewModel) :
    RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>() {
    var comment = mutableListOf<ResponseDetail.Data.Comment>()

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

        fun onBind(comment: ResponseDetail.Data.Comment) {
            binding.replyParentData = comment
            if (comment.children.isEmpty()) {
                val replyAdapter = ReplyChildAdapter(context, adapterPosition, viewModel)
                binding.rcvReplyparentChild.adapter = replyAdapter
                binding.rcvReplyparentChild.layoutManager = LinearLayoutManager(context)
                replyAdapter.setListItems(comment.children)
                Log.d("test", "${comment.children.size}")
            } else {
                binding.rcvReplyparentChild.visibility = View.GONE
            }
        }

        val open_btn: TextView = binding.txtReplyparentOpen
        val child_rcv: RecyclerView = binding.rcvReplyparentChild
        val dot: ImageView = binding.imgReplyparentDot3
        val addReply: TextView = binding.txtReplyparentAdd
        val profile: ImageView = binding.imgReplyparentProfile
    }

    override fun getItemCount(): Int = comment.size

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        holder.onBind(comment[position])

        holder.onBind(comment[position]).let {
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
                if (comment[position].children.isEmpty()) {
                    open_btn.visibility = View.GONE
                } else {
                    val replyAdapter = ReplyChildAdapter(context, adapterPosition, viewModel)
                    child_rcv.adapter = replyAdapter
                    child_rcv.layoutManager = LinearLayoutManager(context)
                    replyAdapter.setListItems(comment[position].children)
                    open_btn.visibility = View.VISIBLE
                }
                dot.setOnClickListener {
                    viewModel.setPosition(position)
                }
                child_rcv.setOnClickListener {
                    viewModel.setPosition(position)
                }
                addReply.setOnClickListener {
                    viewModel.setReplyPosition(position)
                    viewModel.addReplyChildClicked()
                }
                profile.setOnClickListener { view ->
                    val intent = Intent(view.context, OtherPageActivity::class.java)
                    intent.putExtra("userId", comment[position].userId)
                    if (comment[position].isAuthor)
                        intent.putExtra("isAuthor", true)
                    view.context.startActivity(intent)
                }
            }
        }
    }

    fun replaceReplyList(comment: List<ResponseDetail.Data.Comment>) {
        this.comment = comment.toMutableList()
        notifyDataSetChanged()
    }
}