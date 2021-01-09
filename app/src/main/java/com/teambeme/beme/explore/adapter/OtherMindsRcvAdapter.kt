package com.teambeme.beme.explore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemExploreOtherMindsBinding
import com.teambeme.beme.explore.model.OtherMindsData
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherMindsRcvAdapter(private val context: Context) :
    ListAdapter<OtherMindsData, OtherMindsRcvAdapter.OtherMindsRcvViewHolder>(OtherMindsDiffUtil()) {
    inner class OtherMindsRcvViewHolder(private val binding: ItemExploreOtherMindsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherMindsData: OtherMindsData) {
            binding.otherMinds = otherMindsData
            binding.executePendingBindings()
            setClickListenerForShowOtherAnswers(binding, otherMindsData, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherMindsRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemExploreOtherMindsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_explore_other_minds, parent, false)
        return OtherMindsRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherMindsRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherMindsDiffUtil : DiffUtil.ItemCallback<OtherMindsData>() {
        override fun areItemsTheSame(oldItem: OtherMindsData, newItem: OtherMindsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherMindsData, newItem: OtherMindsData) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ItemExploreOtherMindsBinding,
        otherMindsData: OtherMindsData,
        context: Context
    ) {
        binding.btnOtherMindsShowOtherAnswers.setOnClickListener {
            context.startActivity<ExploreDetailActivity>(otherMindsData.title)
        }
    }
}
