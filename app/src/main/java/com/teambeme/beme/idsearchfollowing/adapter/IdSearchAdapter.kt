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
import com.teambeme.beme.otherpage.view.OtherPageActivity

class IdSearchAdapter : ListAdapter<ResponseIdSearchData.Data, IdSearchAdapter.IdSearchViewHolder>(
    IdSearchDiffUtil()
) {
    class IdSearchViewHolder(private val binding: ItemFollowingAfterIdsearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(idSearchData: ResponseIdSearchData.Data) {
            binding.idSearch = idSearchData
            Log.d("Network is success_2", idSearchData.toString())
        }

        val userProfilePic = binding.idSearchProfilePic

        private fun setBtnUnfollowClickListener(
            binding: ItemFollowingAfterIdsearchBinding
        ) {
            binding.btnFollowingUnfollow.setOnClickListener {
                binding.btnFollowingFollow.visibility = View.VISIBLE
                binding.btnFollowingUnfollow.visibility = View.INVISIBLE
            }
        }

        private fun setBtnFollowClickListener(
            binding: ItemFollowingAfterIdsearchBinding
        ) {
            binding.btnFollowingFollow.setOnClickListener {
                binding.btnFollowingFollow.visibility = View.INVISIBLE
                binding.btnFollowingUnfollow.visibility = View.VISIBLE
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

    private var idSearchDatas = mutableListOf<ResponseIdSearchData.Data>()
}

