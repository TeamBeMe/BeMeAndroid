package com.teambeme.beme.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemMywriteBinding
import com.teambeme.beme.mypage.model.MyWrite

class MyWriteAdapter :
    ListAdapter<MyWrite, MyWriteAdapter.MyWriteViewHolder>(MyWriteDiffUtil()) {
    private var writeList = mutableListOf<MyWrite>()

    class MyWriteViewHolder(private val binding: ItemMywriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(write: MyWrite) {
            binding.myWrite = write
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMywriteBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_mywrite, parent, false)
        return MyWriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class MyWriteDiffUtil : DiffUtil.ItemCallback<MyWrite>() {
        override fun areItemsTheSame(oldItem: MyWrite, newItem: MyWrite) =
            (oldItem.question == newItem.question)

        override fun areContentsTheSame(oldItem: MyWrite, newItem: MyWrite) =
            (oldItem == newItem)
    }

    fun replaceWriteList(list: List<MyWrite>) {
        writeList = list.toMutableList()
        submitList(writeList)
    }
}