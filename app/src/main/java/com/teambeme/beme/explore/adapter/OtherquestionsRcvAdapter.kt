package com.teambeme.beme.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ListExploreOtherquestionsBinding
import com.teambeme.beme.explore.model.OtherquestionsData

class OtherquestionsRcvAdapter :
    ListAdapter<OtherquestionsData, OtherquestionsRcvAdapter.OtherquestionsRcvViewHolder>(
        OtherquestionsDiffUtil()
    ) {
    inner class OtherquestionsRcvViewHolder(private val binding: ListExploreOtherquestionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherquestionsData: OtherquestionsData) {
            binding.otherquestions = otherquestionsData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherquestionsRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListExploreOtherquestionsBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_explore_otherquestions,
                parent,
                false
            )
        return OtherquestionsRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherquestionsRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherquestionsDiffUtil : DiffUtil.ItemCallback<OtherquestionsData>() {
        override fun areItemsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
            (oldItem == newItem)
    }
}
