package com.teambeme.beme.explore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemExploreOtherMindsBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.explore.model.ResponseExplorationMinds
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherMindsRcvAdapter(private val context: Context) :
    ListAdapter<ResponseExplorationMinds.Data, OtherMindsRcvAdapter.OtherMindsRcvViewHolder>(OtherMindsDiffUtil()) {
    inner class OtherMindsRcvViewHolder(private val binding: ItemExploreOtherMindsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherMindsData: ResponseExplorationMinds.Data) {
            binding.otherMinds = otherMindsData
            binding.executePendingBindings()
            setClickListenerForShowOtherAnswers(binding, otherMindsData, context)
            setClickListenerForGoDetail(binding, otherMindsData, context)
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

    private class OtherMindsDiffUtil : DiffUtil.ItemCallback<ResponseExplorationMinds.Data>() {
        override fun areItemsTheSame(oldItem: ResponseExplorationMinds.Data, newItem: ResponseExplorationMinds.Data) =
            (oldItem.questionId == newItem.questionId)

        override fun areContentsTheSame(oldItem: ResponseExplorationMinds.Data, newItem: ResponseExplorationMinds.Data) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ItemExploreOtherMindsBinding,
        otherMindsData: ResponseExplorationMinds.Data,
        context: Context
    ) {
        binding.btnOtherMindsShowOtherAnswers.setOnClickListener {
            context.startActivity<ExploreDetailActivity>(otherMindsData.questionTitle, otherMindsData.questionId)
        }
    }

    private fun setClickListenerForGoDetail(
        binding: ItemExploreOtherMindsBinding,
        otherMindsData: ResponseExplorationMinds.Data,
        context: Context
    ) {
        binding.constraintlayoutOtherMindsContent.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", otherMindsData.id)
            context.startActivity(intent)
        }
    }
}
