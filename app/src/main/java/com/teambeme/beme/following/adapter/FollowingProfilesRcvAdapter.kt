package com.teambeme.beme.following.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemFollowingOtherProfilesBinding
import com.teambeme.beme.following.model.FollowingProfilesData

class FollowingProfilesRcvAdapter(private val context: Context) :
    ListAdapter<FollowingProfilesData, FollowingProfilesRcvAdapter.FollowingProfilesRcvViewHolder>(FollowingProfilesDiffUtil()) {
    inner class FollowingProfilesRcvViewHolder(private val binding: ItemFollowingOtherProfilesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followingProfilesData: FollowingProfilesData) {
            binding.followingProfiles = followingProfilesData
//            //프로필 사진 누르면 타인 프로필 페이지로 이동
//            setClickListenerForGoProfilePage(binding, followingProfilesData, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingProfilesRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowingOtherProfilesBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_following_other_profiles, parent, false)
        return FollowingProfilesRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingProfilesRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class FollowingProfilesDiffUtil : DiffUtil.ItemCallback<FollowingProfilesData>() {
        override fun areItemsTheSame(oldItem: FollowingProfilesData, newItem: FollowingProfilesData) =
            (oldItem.profile_id == newItem.profile_id)

        override fun areContentsTheSame(oldItem: FollowingProfilesData, newItem: FollowingProfilesData) =
            (oldItem == newItem)
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
