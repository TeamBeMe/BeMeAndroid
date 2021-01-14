package com.teambeme.beme.mypage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemMywriteBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyWriteAdapter(private val myViewModel: MyPageViewModel) :
    ListAdapter<ResponseMyAnswer.Data.Answer, MyWriteAdapter.MyWriteViewHolder>(MyWriteDiffUtil()) {
    private var writeList = mutableListOf<ResponseMyAnswer.Data.Answer>()

    class MyWriteViewHolder(private val binding: ItemMywriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(write: ResponseMyAnswer.Data.Answer) {
            binding.myWrite = write
        }

        val secretBtn: ImageButton = binding.imgMywriteSecret
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMywriteBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_mywrite, parent, false)
        return MyWriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWriteViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.putExtra("answerId", getItem(position).id)
            view.context.startActivity(intent)
        }
        holder.bind(getItem(position)).let {
            with(holder) {
                secretBtn.setOnClickListener {
                    myViewModel.setPublicPosition(position)
                    if (getItem(position).publicFlag) {
                        secretBtn.setImageResource(R.drawable.ic_secret_on_mypage)
                    } else {
                        secretBtn.setImageResource(R.drawable.ic_secret_off_mypage)
                    }
                }
            }
        }
    }

    private class MyWriteDiffUtil : DiffUtil.ItemCallback<ResponseMyAnswer.Data.Answer>() {
        override fun areItemsTheSame(
            oldItem: ResponseMyAnswer.Data.Answer,
            newItem: ResponseMyAnswer.Data.Answer
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseMyAnswer.Data.Answer,
            newItem: ResponseMyAnswer.Data.Answer
        ) =
            (oldItem == newItem)
    }

    fun replaceWriteList(list: List<ResponseMyAnswer.Data.Answer>) {
        writeList = list.toMutableList()
        submitList(writeList)
    }
}