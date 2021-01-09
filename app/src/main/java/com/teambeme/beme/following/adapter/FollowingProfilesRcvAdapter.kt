package com.teambeme.beme.following.adapter

import android.content.Context
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
import com.teambeme.beme.databinding.ItemFollowingOtherProfilesBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesBinding
import com.teambeme.beme.following.model.FollowingProfilesData

class FollowingProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int
) :
    ListAdapter<FollowingProfilesData, FollowingProfilesRcvAdapter<B>.FollowingProfilesRcvViewHolder<B>>(
        FollowingProfilesDiffUtil()
    ) {
    inner class FollowingProfilesRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followingProfilesData: FollowingProfilesData) {
            when (binding) {
                is ItemFollowingOtherProfilesBinding -> {
                    with(binding as ItemFollowingOtherProfilesBinding) {
                        executePendingBindings()
                        setVariable(BR.followingProfiles, followingProfilesData)
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesBinding) {
                        executePendingBindings()
                        setVariable(BR.followingShowAllProfiles, followingProfilesData)
                        setClickListenerForFollowBtn(binding, followingProfilesData)
                        setclickListenerForFollowingBtn(binding, followingProfilesData)
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
    ): FollowingProfilesRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return FollowingProfilesRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingProfilesRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class FollowingProfilesDiffUtil : DiffUtil.ItemCallback<FollowingProfilesData>() {
        override fun areItemsTheSame(
            oldItem: FollowingProfilesData,
            newItem: FollowingProfilesData
        ) =
            (oldItem.profile_id == newItem.profile_id)

        override fun areContentsTheSame(
            oldItem: FollowingProfilesData,
            newItem: FollowingProfilesData
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForFollowBtn(
        binding: ItemFollowingShowAllProfilesBinding,
        followingProfilesData: FollowingProfilesData
    ) {
        binding.btnFollowingShowAllFollow.setOnClickListener {
            followingProfilesData.isFollowing = true
            binding.btnFollowingShowAllFollow.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollowing.visibility = View.VISIBLE
            Log.d("tag", followingProfilesData.isFollowing.toString())
        }
    }

    private fun setclickListenerForFollowingBtn(
        binding: ItemFollowingShowAllProfilesBinding,
        followingProfilesData: FollowingProfilesData
    ) {
        binding.btnFollowingShowAllFollowing.setOnClickListener {
            followingProfilesData.isFollowing = false
            binding.btnFollowingShowAllFollowing.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollow.visibility = View.VISIBLE
            Log.d("tag", followingProfilesData.isFollowing.toString())
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
