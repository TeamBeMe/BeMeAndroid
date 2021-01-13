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
import com.teambeme.beme.databinding.ItemFollowingProfilesOfFollowingBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfFollowingBinding
import com.teambeme.beme.following.model.ResponseFollowingList

class FollowingProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int
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
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesOfFollowingBinding) {
                        setVariable(BR.showAllProfilesFollowing, followingProfilesData)
                        executePendingBindings()
                        Log.d("showAll__", showAllProfilesFollowing.toString())
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
        followingProfilesData: ResponseFollowingList.Data.Followee
    ) {
        binding.btnFollowingShowAllFollow.setOnClickListener {
            binding.btnFollowingShowAllFollow.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollowing.visibility = View.VISIBLE
        }
    }

    private fun setclickListenerForFollowingBtn(
        binding: ItemFollowingShowAllProfilesOfFollowingBinding,
        followingProfilesData: ResponseFollowingList.Data.Followee
    ) {
        binding.btnFollowingShowAllFollowing.setOnClickListener {
            binding.btnFollowingShowAllFollowing.visibility = View.INVISIBLE
            binding.btnFollowingShowAllFollow.visibility = View.VISIBLE
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
