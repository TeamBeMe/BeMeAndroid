package com.teambeme.beme.idsearchfollowing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemFollowingAfterIdsearchBinding
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData
import com.teambeme.beme.util.recordClickEvent

class IdSearchAdapter(
    private val followButtonEvent: FollowButton
) : ListAdapter<ResponseIdSearchData.Data, IdSearchAdapter.IdSearchViewHolder>(IdSearchDiffUtil) {
    inner class IdSearchViewHolder(
        private val binding: ItemFollowingAfterIdsearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val userProfilePic = binding.idSearchProfilePic

        fun bind(idSearchData: ResponseIdSearchData.Data) {
            with(binding) {
                idSearch = idSearchData
                btnFollowingFollowing.setOnClickListener {
                    followButtonEvent.setOnUnfollowClickListener(idSearchData.id)
                    recordClickEvent("BUTTON", "FOLLOW_SEARCHID_FALSE")
                    binding.btnFollowingFollow.visibility = View.VISIBLE
                    binding.btnFollowingFollowing.visibility = View.INVISIBLE
                }
                btnFollowingFollow.setOnClickListener {
                    followButtonEvent.setOnFollowClickListener(idSearchData.id)
                    recordClickEvent("BUTTON", "FOLLOW_SEARCHID_TRUE")
                    binding.btnFollowingFollow.visibility = View.INVISIBLE
                    binding.btnFollowingFollowing.visibility = View.VISIBLE
                }
                setFollowingFollowBtn(binding, idSearchData)
                executePendingBindings()
            }
        }

//        private fun setBtnUnfollowClickListener(
//            binding: ItemFollowingAfterIdsearchBinding,
//            data: ResponseIdSearchData.Data,
//            viewModel: IdSearchViewModel
//        ) {
//            binding.btnFollowingFollowing.setOnClickListener {
//                viewModel.requestFollowAndFollowing(data.id)
//                recordClickEvent("BUTTON", "FOLLOW_SEARCHID_FALSE")
//                binding.btnFollowingFollow.visibility = View.VISIBLE
//                binding.btnFollowingFollowing.visibility = View.INVISIBLE
//            }
//        }
//
//        private fun setBtnFollowClickListener(
//            binding: ItemFollowingAfterIdsearchBinding,
//            data: ResponseIdSearchData.Data,
//            viewModel: IdSearchViewModel
//        ) {
//            binding.btnFollowingFollow.setOnClickListener {
//                viewModel.requestFollowAndFollowing(data.id)
//                recordClickEvent("BUTTON", "FOLLOW_SEARCHID_TRUE")
//                binding.btnFollowingFollow.visibility = View.INVISIBLE
//                binding.btnFollowingFollowing.visibility = View.VISIBLE
//            }
//        }

        private fun setFollowingFollowBtn(
            binding: ItemFollowingAfterIdsearchBinding,
            data: ResponseIdSearchData.Data
        ) {
            if (data.isFollowed == true) {
                binding.btnFollowingFollowing.visibility = View.VISIBLE
                binding.btnFollowingFollow.visibility = View.INVISIBLE
            } else {
                binding.btnFollowingFollowing.visibility = View.INVISIBLE
                binding.btnFollowingFollow.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowingAfterIdsearchBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_following_after_idsearch,
                parent,
                false
            )
        return IdSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IdSearchViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
            userProfilePic.setOnClickListener {
                followButtonEvent.setProfilePicClickListener(getItem(position).id)
            }
        }
    }

    private object IdSearchDiffUtil : DiffUtil.ItemCallback<ResponseIdSearchData.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseIdSearchData.Data,
            newItem: ResponseIdSearchData.Data
        ) = oldItem.isFollowed == newItem.isFollowed

        override fun areContentsTheSame(
            oldItem: ResponseIdSearchData.Data,
            newItem: ResponseIdSearchData.Data
        ) = oldItem == newItem
    }

    interface FollowButton {
        fun setOnUnfollowClickListener(id: Int)
        fun setOnFollowClickListener(id: Int)
        fun setProfilePicClickListener(id: Int)
    }
}