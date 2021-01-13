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
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfSearchFollowerBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesOfSearchFollowingBinding
import com.teambeme.beme.following.model.ResponseFollowingSearchId

class SearchProfilesRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int
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
                        setClickListenerForFollowBtn(binding, searchData)
                        setclickListenerForFollowingBtn(binding, searchData)
                        Log.d("showAll", searchData.toString())
                    }
                }
                else -> {
                    with(binding as ItemFollowingShowAllProfilesOfSearchFollowerBinding) {
                        setVariable(BR.searchFollower, searchData)
                        executePendingBindings()
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
        followingProfilesData: ResponseFollowingSearchId.Data
    ) {
        binding.btnFollowingShowAllFollow.setOnClickListener {
            binding.btnFollowingShowAllFollow.visibility = View.VISIBLE
            binding.btnFollowingShowAllFollowing.visibility = View.INVISIBLE
        }
    }

    private fun setclickListenerForFollowingBtn(
        binding: ItemFollowingShowAllProfilesOfSearchFollowingBinding,
        followingProfilesData: ResponseFollowingSearchId.Data
    ) {
        binding.btnFollowingShowAllFollow.setOnClickListener {
            binding.btnFollowingShowAllFollow.visibility = View.VISIBLE
            binding.btnFollowingShowAllFollowing.visibility = View.INVISIBLE
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
