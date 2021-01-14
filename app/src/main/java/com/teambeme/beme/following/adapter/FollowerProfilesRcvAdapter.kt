package com.teambeme.beme.following.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.BR
import com.teambeme.beme.databinding.ItemFollowingProfilesOfFollowerBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfFollowerBinding
import com.teambeme.beme.following.model.ResponseFollowingList
import com.teambeme.beme.following.viewmodel.FollowingViewModel

class FollowerProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int,
    private val viewModel: FollowingViewModel
) :
    ListAdapter<ResponseFollowingList.Data.Follower, FollowerProfilesRcvAdapter<B>.FollowerProfilesRcvViewHolder<B>>(
        FollowerProfilesDiffUtil()
    ) {
    inner class FollowerProfilesRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followerProfilesData: ResponseFollowingList.Data.Follower) {
            when (binding) {
                is ItemFollowingProfilesOfFollowerBinding -> {
                    with(binding as ItemFollowingProfilesOfFollowerBinding) {
                        setVariable(BR.followerProfiles, followerProfilesData)
                        executePendingBindings()
                        Log.d("showAll", followerProfiles.toString())
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesOfFollowerBinding) {
                        setVariable(BR.showAllProfilesFollower, followerProfilesData)
                        executePendingBindings()
                        Log.d("showAll__", showAllProfilesFollower.toString())
                        setClickListenerForDeleteFollower(binding, followerProfilesData, viewModel)
                    }
                }
            }
//            //프로필 사진 누르면 타인 프로필 페이지로 이동
//            setClickListenerForGoProfilePage(binding, followingProfilesData, context)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerProfilesRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return FollowerProfilesRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerProfilesRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class FollowerProfilesDiffUtil :
        DiffUtil.ItemCallback<ResponseFollowingList.Data.Follower>() {
        override fun areItemsTheSame(
            oldItem: ResponseFollowingList.Data.Follower,
            newItem: ResponseFollowingList.Data.Follower
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseFollowingList.Data.Follower,
            newItem: ResponseFollowingList.Data.Follower
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForDeleteFollower(
        binding: ItemFollowingShowAllProfilesOfFollowerBinding,
        data: ResponseFollowingList.Data.Follower,
        viewModel: FollowingViewModel
    ) {
        binding.btnFollowingShowAllDeleteFollower.setOnClickListener {
            viewModel.requestDeleteFollower(data.id)
            viewModel.requestFollowerFollowingList()
        }
    }

//    //프로필 사진 누르면 타인 프로필 페이지로 이동
//    private fun setClickListenerForGoProfilePage(
//        binding: ItemFollowingOtherProfilesBinding,
//        followingProfilesData: FollowingProfilesData,
//        context: Context
//    ) {
//        binding.imgFollowingOtherProfile.setOnClickListener {
//            context.startActivity</*타인프로필페이지 액티비티*/>(/*string값(서버 붙이면 안보내도 될듯*/)
//        }
//    }
}
