package com.teambeme.beme.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.BR
import com.teambeme.beme.R
import com.teambeme.beme.explore.model.OtherquestionsData

class OtherquestionsRcvAdapter<B : ViewDataBinding>(private val layout: Int) :
    ListAdapter<OtherquestionsData, OtherquestionsRcvAdapter<B>.OtherquestionsRcvViewHolder<B>>(
        OtherquestionsDiffUtil()
    ) {
    inner class OtherquestionsRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherquestionsData: OtherquestionsData) {
            when (layout) {
                R.layout.list_explore_otherquestions -> binding.setVariable(
                    BR.otherquestions,
                    otherquestionsData
                )
                R.layout.list_exploredetail_otheranswers -> binding.setVariable(
                    BR.otheranswers,
                    otherquestionsData
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherquestionsRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(
                layoutInflater,
                layout,
                parent,
                false
            )
        return OtherquestionsRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherquestionsRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherquestionsDiffUtil : DiffUtil.ItemCallback<OtherquestionsData>() {
        override fun areItemsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherquestionsData, newItem: OtherquestionsData) =
            (oldItem == newItem)
    }
}
