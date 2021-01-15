package com.teambeme.beme.idsearchfollowing.adapter

import android.content.Intent
import android.util.Log
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
import com.teambeme.beme.idsearchfollowing.viewmodel.IdSearchViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity

class IdSearchAdapter(
    private val viewModel: IdSearchViewModel
) : ListAdapter<ResponseIdSearchData.Data, IdSearchAdapter.IdSearchViewHolder>(
    IdSearchDiffUtil()
) {
    inner class IdSearchViewHolder(private val binding: ItemFollowingAfterIdsearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(idSearchData: ResponseIdSearchData.Data) {
            with(binding) {
                binding.idSearch = idSearchData
                executePendingBindings()
                Log.d("Network is success_2", idSearchData.toString())
                setBtnFollowClickListener(binding, idSearchData, viewModel)
                setBtnUnfollowClickListener(binding, idSearchData, viewModel)
                setFollowingFollowBtn(binding, viewModel)
            }
        }

        val userProfilePic = binding.idSearchProfilePic
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
        holder.bind(getItem(position))
        holder.bind(getItem(position)).let {
            with(holder) {
                userProfilePic.setOnClickListener { view ->
                    val intent = Intent(view.context, OtherPageActivity::class.java)
                    intent.putExtra("userId", getItem(position).id)
                    Log.d("Internt", position.toString())
                    Log.d("Internt", getItem(position).id.toString())
                    view.context.startActivity(intent)
                }
            }
        }
    }

    private class IdSearchDiffUtil : DiffUtil.ItemCallback<ResponseIdSearchData.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseIdSearchData.Data,
            newItem: ResponseIdSearchData.Data
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseIdSearchData.Data,
            newItem: ResponseIdSearchData.Data
        ) =
            (oldItem == newItem)
    }

    private fun setBtnUnfollowClickListener(
        binding: ItemFollowingAfterIdsearchBinding,
        data: ResponseIdSearchData.Data,
        viewModel: IdSearchViewModel
    ) {
        binding.btnFollowingFollowing.setOnClickListener {
            Log.d("btn", "팔로우")
            viewModel.requestFollowAndFollowing(data.id)
            binding.btnFollowingFollow.visibility = View.VISIBLE
            binding.btnFollowingFollowing.visibility = View.INVISIBLE
        }
    }

    private fun setBtnFollowClickListener(
        binding: ItemFollowingAfterIdsearchBinding,
        data: ResponseIdSearchData.Data,
        viewModel: IdSearchViewModel
    ) {
        binding.btnFollowingFollow.setOnClickListener {
            Log.d("btn", "팔로잉")
            viewModel.requestFollowAndFollowing(data.id)
            binding.btnFollowingFollow.visibility = View.INVISIBLE
            binding.btnFollowingFollowing.visibility = View.VISIBLE
        }
    }

    private fun setFollowingFollowBtn(
        binding: ItemFollowingAfterIdsearchBinding,
        viewModel: IdSearchViewModel
    ) {
        if (viewModel.isFollowed.value == true) {
            binding.btnFollowingFollowing.visibility = View.VISIBLE
            binding.btnFollowingFollow.visibility = View.INVISIBLE
        } else {
            binding.btnFollowingFollowing.visibility = View.INVISIBLE
            binding.btnFollowingFollow.visibility = View.VISIBLE
        }
    }

}

