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
import com.teambeme.beme.databinding.ItemFollowingProfilesOfFollowingBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfFollowingBinding
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity

class FollowingProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int,
    private val viewModel: FollowingViewModel
) :
    ListAdapter<ResponseFollowingList.Data.Followee, FollowingProfilesRcvAdapter<B>.FollowingProfilesRcvViewHolder<B>>(
        FollowingProfilesDiffUtil()
    ) {
    inner class FollowingProfilesRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followingProfilesData: ResponseFollowingList.Data.Followee) {
            when (binding) {
                is ItemFollowingProfilesOfFollowingBinding -> {
                    with(binding as ItemFollowingProfilesOfFollowingBinding) {
                        setVariable(BR.followingProfiles, followingProfilesData)
                        executePendingBindings()
                        Log.d("showAll", followingProfiles.toString())
                        setClickListenerForGoProfilePageFromFragment(
                            binding,
                            followingProfilesData,
                            context
                        )
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesOfFollowingBinding) {
                        setVariable(BR.showAllProfilesFollowing, followingProfilesData)
                        executePendingBindings()
                        Log.d("showAll__", showAllProfilesFollowing.toString())
                        setClickListenerForFollowBtn(binding, followingProfilesData, viewModel)
                        setclickListenerForFollowingBtn(binding, followingProfilesData, viewModel)
                        setClickListenerForGoProfilePageFromActivity(
                            binding,
                            followingProfilesData,
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
    ): FollowingProfilesRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return FollowingProfilesRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingProfilesRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class FollowingProfilesDiffUtil :
        DiffUtil.ItemCallback<ResponseFollowingList.Data.Followee>() {
        override fun areItemsTheSame(
            oldItem: ResponseFollowingList.Data.Followee,
            newItem: ResponseFollowingList.Data.Followee
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseFollowingList.Data.Followee,
            newItem: ResponseFollowingList.Data.Followee
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForFollowBtn(
        binding: ItemFollowingShowAllProfilesOfFollowingBinding,
        followingProfilesData: ResponseFollowingList.Data.Followee,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllFollow.setOnClickListener {
            Log.d("btn2", "팔로우")
            viewModel.requestFollow(followingProfilesData.id)
            binding.btnFollowingShowAllFollow.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollowing.visibility = View.VISIBLE
        }
    }

    private fun setclickListenerForFollowingBtn(
        binding: ItemFollowingShowAllProfilesOfFollowingBinding,
        followingProfilesData: ResponseFollowingList.Data.Followee,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllFollowing.setOnClickListener {
            Log.d("btn2", "팔로잉")
            viewModel.requestFollow(followingProfilesData.id)
            binding.btnFollowingShowAllFollowing.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollow.visibility = View.VISIBLE
        }
    }

    private fun setClickListenerForGoProfilePageFromActivity(
        binding: ItemFollowingShowAllProfilesOfFollowingBinding,
        data: ResponseFollowingList.Data.Followee,
        context: Context
    ) {
        binding.imgFollowingShowAllProfiles.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.id)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoProfilePageFromFragment(
        binding: ItemFollowingProfilesOfFollowingBinding,
        data: ResponseFollowingList.Data.Followee,
        context: Context
    ) {
        binding.imgFollowingOtherProfile.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.id)
            context.startActivity(intent)
        }
    }
}
