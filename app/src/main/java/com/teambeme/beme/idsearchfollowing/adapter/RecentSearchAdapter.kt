package com.teambeme.beme.idsearchfollowing.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemRecentSearchBinding
import com.teambeme.beme.idsearchfollowing.model.RecentSearchData
import com.teambeme.beme.idsearchfollowing.model.ResponseRecentSearchRecord
import com.teambeme.beme.idsearchfollowing.viewmodel.IdSearchViewModel

class RecentSearchAdapter(private val idSearchViewModel: IdSearchViewModel) :
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {
    private var recentSearchData = mutableListOf<ResponseRecentSearchRecord.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRecentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_recent_search, parent, false)
        return RecentSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = recentSearchData.size

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(recentSearchData[position])
        holder.bind(recentSearchData[position]).let {
        }
        holder.btnDeleteRecentRecearch.setOnClickListener {
            idSearchViewModel.setPosition(position)
            recentSearchData.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun replaceRecentSearchList(list: MutableList<ResponseRecentSearchRecord.Data>) {
        recentSearchData = list
        notifyDataSetChanged()
    }

    inner class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearchData: ResponseRecentSearchRecord.Data) {
            with(binding) {
                recentSearch = recentSearchData
                executePendingBindings()
            }
            Log.d("Network is success_2", recentSearchData.toString())
        }
        val btnDeleteRecentRecearch = binding.btnDeleteRecentSearch
    }
}