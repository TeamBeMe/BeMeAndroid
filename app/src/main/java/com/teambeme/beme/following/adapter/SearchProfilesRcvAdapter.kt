package com.teambeme.beme.following.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.BR
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfSearchFollowerBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfSearchFollowingBinding
import com.teambeme.beme.following.model.ResponseFollowingSearchId
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity

class SearchProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int,
    private val viewModel: FollowingViewModel
) :
    ListAdapter<ResponseFollowingSearchId.Data, SearchProfilesRcvAdapter<B>.SearchProfilesRcvViewHolder<B>>(
        SearchProfilesDiffUtil()
    ) {
    inner class SearchProfilesRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchData: ResponseFollowingSearchId.Data) {
            when (binding) {
                is ItemFollowingShowAllProfilesOfSearchFollowingBinding -> {
                    with(binding as ItemFollowingShowAllProfilesOfSearchFollowingBinding) {
                        setVariable(BR.searchFollowing, searchData)
                        executePendingBindings()
                        setClickListenerForFollowBtn(binding, searchData, viewModel)
                        setclickListenerForFollowingBtn(binding, searchData, viewModel)
                        setClickListenerForGoProfilePageFromSearchFollowing(
                            binding,
                            searchData,
                            context
                        )
                        Log.d("showAll", searchData.toString())
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesOfSearchFollowerBinding) {
                        setVariable(BR.searchFollower, searchData)
                        executePendingBindings()
                        setClickListenerForDeleteFollower(binding, searchData, viewModel)
                        setClickListenerForGoProfilePageFromSearchFollower(
                            binding,
                            searchData,
                            context
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProfilesRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return SearchProfilesRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProfilesRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class SearchProfilesDiffUtil : DiffUtil.ItemCallback<ResponseFollowingSearchId.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseFollowingSearchId.Data,
            newItem: ResponseFollowingSearchId.Data
        ) =
            (oldItem.isFollowed == newItem.isFollowed)

        override fun areContentsTheSame(
            oldItem: ResponseFollowingSearchId.Data,
            newItem: ResponseFollowingSearchId.Data
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForFollowBtn(
        binding: ItemFollowingShowAllProfilesOfSearchFollowingBinding,
        data: ResponseFollowingSearchId.Data,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllFollowSearch.setOnClickListener {
            Log.d("btn", "팔로우")
            viewModel.requestFollow(data.id)
            binding.btnFollowingShowAllFollowSearch.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollowingSearch.visibility = View.VISIBLE
        }
    }

    private fun setclickListenerForFollowingBtn(
        binding: ItemFollowingShowAllProfilesOfSearchFollowingBinding,
        data: ResponseFollowingSearchId.Data,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllFollowingSearch.setOnClickListener {
            Log.d("btn", "팔로잉")
            viewModel.requestFollow(data.id)
            binding.btnFollowingShowAllFollowingSearch.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollowSearch.visibility = View.VISIBLE
        }
    }

    private fun setClickListenerForDeleteFollower(
        binding: ItemFollowingShowAllProfilesOfSearchFollowerBinding,
        data: ResponseFollowingSearchId.Data,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllDeleteFollowerSearch.setOnClickListener {
            viewModel.requestDeleteFollower(data.id)
            viewModel.requestSearchMyFollowingFollower()
        }
    }

    private fun setClickListenerForGoProfilePageFromSearchFollowing(
        binding: ItemFollowingShowAllProfilesOfSearchFollowingBinding,
        data: ResponseFollowingSearchId.Data,
        context: Context
    ) {
        binding.imgFollowingShowAllProfiles.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.id)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoProfilePageFromSearchFollower(
        binding: ItemFollowingShowAllProfilesOfSearchFollowerBinding,
        data: ResponseFollowingSearchId.Data,
        context: Context
    ) {
        binding.imgFollowingShowAllProfiles.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.id)
            context.startActivity(intent)
        }
    }
}
