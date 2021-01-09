package com.teambeme.beme.idsearchfollowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemFollowingAfterIdsearchBinding
import com.teambeme.beme.idsearchfollowing.model.IdSearchData

class FollowAfterIdSearchAdapter :
    RecyclerView.Adapter<FollowAfterIdSearchAdapter.FollowAfterIdSearchViewHolder>() {
    private var iDSearchDatas = mutableListOf<IdSearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowAfterIdSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowingAfterIdsearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_following_after_idsearch, parent, false)
        return FollowAfterIdSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = iDSearchDatas.size

    override fun onBindViewHolder(holder: FollowAfterIdSearchViewHolder, position: Int) {
        holder.bind(iDSearchDatas[position])

    }

    fun replaceIdSearchList(list: MutableList<IdSearchData>) {
        iDSearchDatas = list
        notifyDataSetChanged()
    }

    inner class FollowAfterIdSearchViewHolder(private val binding: ItemFollowingAfterIdsearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(idSearchData: IdSearchData) {
            with(binding) {
                idSearch = idSearchData
                executePendingBindings()
            }
        }
        val btnFollow = binding.btnFollowingFollow
        val btnUnFollow = binding.btnFollowingUnfollow
    }


}