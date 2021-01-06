package com.teambeme.beme.explore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ListExploreOthermindsBinding
import com.teambeme.beme.explore.model.OthermindsData
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OthermindsRcvAdapter(private val context: Context) :
    ListAdapter<OthermindsData, OthermindsRcvAdapter.OthermindsRcvViewHolder>(OthermindsDiffUtil()) {
    inner class OthermindsRcvViewHolder(private val binding: ListExploreOthermindsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(othermindsData: OthermindsData) {
            binding.otherminds = othermindsData
            binding.btnOthermindslistShowdetail.setOnClickListener {
                context.startActivity<ExploreDetailActivity>(othermindsData.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OthermindsRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListExploreOthermindsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_explore_otherminds, parent, false)
        return OthermindsRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OthermindsRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OthermindsDiffUtil : DiffUtil.ItemCallback<OthermindsData>() {
        override fun areItemsTheSame(oldItem: OthermindsData, newItem: OthermindsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OthermindsData, newItem: OthermindsData) =
            (oldItem == newItem)
    }
}
