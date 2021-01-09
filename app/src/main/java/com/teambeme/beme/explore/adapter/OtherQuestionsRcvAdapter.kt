package com.teambeme.beme.explore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
        OtherQuestionsDiffUtil()
    ) {
    inner class OtherQuestionsRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherQuestionsData: OtherQuestionsData) {
            when (binding) {
                is ItemExploreOtherQuestionsBinding -> {
                    with(binding as ItemExploreOtherQuestionsBinding) {
                        executePendingBindings()
                        setVariable(BR.otherQuestions, otherQuestionsData)
                        setClickListenerForQuestionsBookmark(binding, otherQuestionsData)
                        setClickListenerForShowOtherAnswers(binding, otherQuestionsData, context)
                        setUnAnsweredItem(binding, otherQuestionsData)
                    }
                }
                else -> {
                    with(binding as ItemExploreDetailOtherAnswersBinding) {
                        executePendingBindings()
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

    private class OtherQuestionsDiffUtil : DiffUtil.ItemCallback<OtherQuestionsData>() {
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
            when (binding.otherQuestions?.isBookmarked) {
                false -> {
                    otherQuestionsData.isBookmarked = true
                    binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otherQuestionsData.isBookmarked = false
                    binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }

    private fun setClickListenerForAnswersBookmark(
        binding: ItemExploreDetailOtherAnswersBinding,
        otherAnswersData: OtherQuestionsData
    ) {
        binding.btnOtherAnswersBookmark.setOnClickListener {
            when (binding.otherAnswers?.isBookmarked) {
                false -> {
                    otherAnswersData.isBookmarked = true
                    binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otherAnswersData.isBookmarked = false
                    binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }

    private fun setUnAnsweredItem(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: OtherQuestionsData
    ) {
        if (!otherQuestionsData.isAnswered) {
            binding.btnOtherQuestionsBookmark.visibility = View.INVISIBLE
            binding.txtOtherQuestionsContent.visibility = View.INVISIBLE
            binding.imgOtherQuestionsProfile.visibility = View.INVISIBLE
            binding.txtOtherQuestionsUserId.visibility = View.INVISIBLE
            binding.btnOtherQuestionsShowOtherAnswers.visibility = View.INVISIBLE
            binding.btnOtherQuestionsDoAnswer.visibility = View.VISIBLE
            binding.txtOtherQuestionsUnAnswered.visibility = View.VISIBLE
        }
    }
}
