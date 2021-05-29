package com.teambeme.beme.setting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemOpensourceBinding
import com.teambeme.beme.setting.model.OpenSourceData

class OpenSourceAdapter :
    ListAdapter<OpenSourceData, OpenSourceAdapter.OpenSourceViewHolder>(OpenSourceDiffCallback()) {
    class OpenSourceViewHolder(private val binding: ItemOpensourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(openSourceData: OpenSourceData) {
            with(binding) {
                data = openSourceData
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenSourceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemOpensourceBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_opensource, parent, false)
        return OpenSourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OpenSourceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OpenSourceDiffCallback : DiffUtil.ItemCallback<OpenSourceData>() {
        override fun areItemsTheSame(oldItem: OpenSourceData, newItem: OpenSourceData): Boolean =
            oldItem.openSource == newItem.openSource

        override fun areContentsTheSame(oldItem: OpenSourceData, newItem: OpenSourceData): Boolean =
            oldItem == newItem
    }
}
