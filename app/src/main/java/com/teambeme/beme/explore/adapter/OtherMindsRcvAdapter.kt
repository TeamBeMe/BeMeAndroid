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
import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherMindsRcvAdapter(private val context: Context) :
    ListAdapter<ResponseExplorationAnswers.Data, OtherMindsRcvAdapter.OtherMindsRcvViewHolder>(OtherMindsDiffUtil()) {
    inner class OtherMindsRcvViewHolder(private val binding: ItemExploreOtherMindsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherMindsData: ResponseExplorationAnswers.Data) {
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

    private class OtherMindsDiffUtil : DiffUtil.ItemCallback<ResponseExplorationAnswers.Data>() {
        override fun areItemsTheSame(oldItem: ResponseExplorationAnswers.Data, newItem: ResponseExplorationAnswers.Data) =
            (oldItem.questionId == newItem.questionId)

        override fun areContentsTheSame(oldItem: ResponseExplorationAnswers.Data, newItem: ResponseExplorationAnswers.Data) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ItemExploreOtherMindsBinding,
        otherMindsData: ResponseExplorationAnswers.Data,
        context: Context
    ) {
        binding.btnOtherMindsShowOtherAnswers.setOnClickListener {
            context.startActivity<ExploreDetailActivity>(otherMindsData.questionTitle)
        }
    }
}
