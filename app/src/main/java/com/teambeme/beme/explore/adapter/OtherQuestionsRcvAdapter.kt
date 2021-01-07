package com.teambeme.beme.explore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.BR
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.explore.model.OtherQuestionsData
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherQuestionsRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int
) :
    ListAdapter<OtherQuestionsData, OtherQuestionsRcvAdapter<B>.OtherQuestionsRcvViewHolder<B>>(
        OtherquestionsDiffUtil()
    ) {
    inner class OtherQuestionsRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherQuestionsData: OtherQuestionsData) {
            when (binding) {
                is ItemExploreOtherQuestionsBinding -> {
                    with(binding as ItemExploreOtherQuestionsBinding) {
                        setVariable(BR.otherQuestions, otherQuestionsData)
                        setClickListenerForQuestionsBookmark(binding, otherQuestionsData)
                        setClickListenerForShowOtherAnswers(binding, otherQuestionsData, context)
                    }
                }
                else -> {
                    with(binding as ItemExploreDetailOtherAnswersBinding) {
                        setVariable(BR.otherAnswers, otherQuestionsData)
                        setClickListenerForAnswersBookmark(binding, otherQuestionsData)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherQuestionsRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(
                layoutInflater,
                layout,
                parent,
                false
            )
        return OtherQuestionsRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherQuestionsRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherquestionsDiffUtil : DiffUtil.ItemCallback<OtherQuestionsData>() {
        override fun areItemsTheSame(oldItem: OtherQuestionsData, newItem: OtherQuestionsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherQuestionsData, newItem: OtherQuestionsData) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: OtherQuestionsData,
        context: Context
    ) {
        binding.btnOtherQuestionsShowOtherAnswers.setOnClickListener {
            otherQuestionsData.title?.let { title ->
                context.startActivity<ExploreDetailActivity>(title)
            }
        }
    }

    private fun setClickListenerForQuestionsBookmark(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: OtherQuestionsData
    ) {
        binding.btnOtherQuestionsBookmark.setOnClickListener {
            when (binding.otherQuestions?.isbookmarked) {
                false -> {
                    otherQuestionsData.isbookmarked = true
                    binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otherQuestionsData.isbookmarked = false
                    binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }

    private fun setClickListenerForAnswersBookmark(
        binding: ItemExploreDetailOtherAnswersBinding,
        otheranswersData: OtherQuestionsData
    ) {
        binding.btnOtherAnswersBookmark.setOnClickListener {
            when (binding.otherAnswers?.isbookmarked) {
                false -> {
                    otheranswersData.isbookmarked = true
                    binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otheranswersData.isbookmarked = false
                    binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }
}
