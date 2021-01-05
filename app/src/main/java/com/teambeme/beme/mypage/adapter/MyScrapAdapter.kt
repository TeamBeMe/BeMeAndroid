package com.teambeme.beme.mypage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemMyscrapBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.mypage.model.MyScrap

class MyScrapAdapter :
    ListAdapter<MyScrap, MyScrapAdapter.MyScrapViewHolder>(MyScrapDiffUtil()) {
    private var scrapList = mutableListOf<MyScrap>()

    class MyScrapViewHolder(private val binding: ItemMyscrapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(scrap: MyScrap) {
            binding.myScrap = scrap
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyScrapViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyscrapBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_myscrap, parent, false)
        return MyScrapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyScrapViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailActivity::class.java)
            view.context.startActivity(intent)

        }
    }

    private class MyScrapDiffUtil : DiffUtil.ItemCallback<MyScrap>() {
        override fun areItemsTheSame(oldItem: MyScrap, newItem: MyScrap) =
            (oldItem.question == newItem.question)

        override fun areContentsTheSame(oldItem: MyScrap, newItem: MyScrap) =
            (oldItem == newItem)
    }

    fun replaceScrapList(list: List<MyScrap>) {
        scrapList = list.toMutableList()
        submitList(scrapList)
    }
}