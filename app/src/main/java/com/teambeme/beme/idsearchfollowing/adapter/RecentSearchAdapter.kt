package com.teambeme.beme.idsearchfollowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentSearchBinding
import com.teambeme.beme.idsearchfollowing.model.RecentSearchData

class RecentSearchAdapter :
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {
    private var recentSearchDatas = mutableListOf<RecentSearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_recent_search, parent, false)
        return RecentSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = recentSearchDatas.size

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(recentSearchDatas[position])
        holder.bind(recentSearchDatas[position]).let {
        }
        holder.btnDeleteRecentRecearch.setOnClickListener {
            recentSearchDatas.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun replaceRecentSearchList(list: MutableList<RecentSearchData>) {
        recentSearchDatas = list
        notifyDataSetChanged()
    }

    inner class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearchData: RecentSearchData) {
            with(binding) {
                recentSearch = recentSearchData
                executePendingBindings()
            }
        }
        val btnDeleteRecentRecearch = binding.btnDeleteRecentSearch
    }
}