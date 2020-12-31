package com.teambeme.beme.reply.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ReplyParentBinding
import com.teambeme.beme.reply.model.ReplyData
import com.teambeme.beme.reply.model.ReplyParentData
import com.teambeme.beme.reply.model.initReplyList
import com.teambeme.beme.reply.viewmodel.ReplyViewModel
import org.w3c.dom.Text

class ReplyParentAdapter(private val context: Context,private val viewModel: ReplyViewModel) :
    ListAdapter<ReplyParentData, ReplyParentAdapter.ReplyParentViewHolder>(
        ReplyDiffUtillCallback
    ) {
    private var replyList= mutableListOf<ReplyParentData>()
    inner class ReplyParentViewHolder(private val binding: ReplyParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ReplyParentData) {
            binding.replyParentData=data
            val dataList = initReplyList()
            val replyAdapter = ReplyChildAdapter()
            binding.rcvReplyparentChild.adapter = replyAdapter

            binding.rcvReplyparentChild.layoutManager = LinearLayoutManager(context)
            replyAdapter.addItem(dataList)
            viewModel.setChildData(data.data_child)




        }
        val open_btn:TextView=binding.txtReplyparentOpen
        val child_rcv:RecyclerView=binding.rcvReplyparentChild

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
        replyList[position].let{item ->
            with(holder){
                open_btn.setOnClickListener {
                    if(open_btn.text=="답글 보기"){
                        child_rcv.visibility = View.VISIBLE
                        open_btn.setText("답글 접기")
                    }else{
                        child_rcv.visibility=View.GONE
                        open_btn.text = "답글 보기"
                    }
                }
                if(replyList[position].data_child[0].txt_id==""){
                    open_btn.visibility=View.GONE
                }

            }
        }




    }

    fun addItem(parentData: MutableList<ReplyParentData>) {
        submitList(parentData)
    }

    fun replaceReplyList(list: List<ReplyParentData>) {
        replyList = list.toMutableList()
        submitList(replyList)
    }




}