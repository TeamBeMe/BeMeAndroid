package com.teambeme.beme.idsearchfollowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemFollowingAfterIdsearchBinding
import com.teambeme.beme.idsearchfollowing.model.ResponseIdSearchData

class IdSearchAdapter :
    RecyclerView.Adapter<IdSearchAdapter.IdSearchViewHolder>() {
    private var idSearchDatas = mutableListOf<ResponseIdSearchData.Data>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):IdSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowingAfterIdsearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_following_after_idsearch, parent, false)
        return IdSearchViewHolder(binding)
    }

    override fun getItemCount(): Int = idSearchDatas.size

    override fun onBindViewHolder(holder: IdSearchViewHolder, position: Int) {
        holder.bind(idSearchDatas[position])
    }

    fun replaceIdSearchList(list: MutableList<ResponseIdSearchData.Data>) {
        idSearchDatas = list
        notifyDataSetChanged()
    }

    inner class IdSearchViewHolder(private val binding: ItemFollowingAfterIdsearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(idSearchData: ResponseIdSearchData.Data) {
            with(binding) {
                idSearch = idSearchData
                executePendingBindings()
            }
//            setBtnUnfollowClickListener(binding)
//            setBtnFollowClickListener(binding)
        }

//        private fun setBtnUnfollowClickListener(
//            binding: ItemFollowingAfterIdsearchBinding
//        ) {
//            binding.btnFollowingUnfollow.setOnClickListener {
//                binding.btnFollowingFollow.visibility = View.VISIBLE
//                binding.btnFollowingUnfollow.visibility = View.INVISIBLE
//            }
//        }
//
//        private fun setBtnFollowClickListener(
//            binding: ItemFollowingAfterIdsearchBinding
//        ) {
//            binding.btnFollowingFollow.setOnClickListener {
//                binding.btnFollowingFollow.visibility = View.INVISIBLE
//                binding.btnFollowingUnfollow.visibility = View.VISIBLE
//            }
//        }
    }
}