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
import com.teambeme.beme.explore.model.OtherMindsData
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherMindsRcvAdapter(private val context: Context) :
    ListAdapter<OtherMindsData, OtherMindsRcvAdapter.OthermindsRcvViewHolder>(OthermindsDiffUtil()) {
    inner class OthermindsRcvViewHolder(private val binding: ListExploreOthermindsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherMindsData: OtherMindsData) {
            binding.otherMinds = otherMindsData
            setClickListenerForShowOtherAnswers(binding, otherMindsData, context)
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

    private class OthermindsDiffUtil : DiffUtil.ItemCallback<OtherMindsData>() {
        override fun areItemsTheSame(oldItem: OtherMindsData, newItem: OtherMindsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherMindsData, newItem: OtherMindsData) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ListExploreOthermindsBinding,
        otherMindsData: OtherMindsData,
        context: Context
    ) {
        binding.btnOthermindslistShowOtherAnswers.setOnClickListener {
            context.startActivity<ExploreDetailActivity>(otherMindsData.title)
        }
    }
}
