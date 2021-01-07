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
import com.teambeme.beme.databinding.ListExploreOtherquestionsBinding
import com.teambeme.beme.databinding.ListExploredetailOtheranswersBinding
import com.teambeme.beme.explore.model.OtherQuestionsData
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.util.startActivity

class OtherQuestionsRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int
) :
    ListAdapter<OtherQuestionsData, OtherQuestionsRcvAdapter<B>.OtherquestionsRcvViewHolder<B>>(
        OtherquestionsDiffUtil()
    ) {
    inner class OtherquestionsRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherQuestionsData: OtherQuestionsData) {
            when (binding) {
                is ListExploreOtherquestionsBinding -> {
                    with(binding as ListExploreOtherquestionsBinding) {
                        setVariable(BR.otherQuestions, otherQuestionsData)
                        setClickListenerForQuestionsBookmark(binding, otherQuestionsData)
                        setClickListenerForShowOtherAnswers(binding, otherQuestionsData, context)
                    }
                }
                else -> {
                    with(binding as ListExploredetailOtheranswersBinding) {
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

    private class OtherquestionsDiffUtil : DiffUtil.ItemCallback<OtherQuestionsData>() {
        override fun areItemsTheSame(oldItem: OtherQuestionsData, newItem: OtherQuestionsData) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: OtherQuestionsData, newItem: OtherQuestionsData) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ListExploreOtherquestionsBinding,
        otherQuestionsData: OtherQuestionsData,
        context: Context
    ) {
        binding.btnOtherquestionslistShowOtherAnswers.setOnClickListener {
            otherQuestionsData.title?.let { title ->
                context.startActivity<ExploreDetailActivity>(title)
            }
        }
    }

    private fun setClickListenerForQuestionsBookmark(
        binding: ListExploreOtherquestionsBinding,
        otherQuestionsData: OtherQuestionsData
    ) {
        binding.btnOtherquestionslistBookmark.setOnClickListener {
            when (binding.otherQuestions?.isbookmarked) {
                false -> {
                    otherQuestionsData.isbookmarked = true
                    binding.btnOtherquestionslistBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otherQuestionsData.isbookmarked = false
                    binding.btnOtherquestionslistBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }

    private fun setClickListenerForAnswersBookmark(
        binding: ListExploredetailOtheranswersBinding,
        otheranswersData: OtherQuestionsData
    ) {
        binding.btnOtheranswerslistBookmark.setOnClickListener {
            when (binding.otherAnswers?.isbookmarked) {
                false -> {
                    otheranswersData.isbookmarked = true
                    binding.btnOtheranswerslistBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                }
                else -> {
                    otheranswersData.isbookmarked = false
                    binding.btnOtheranswerslistBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }
}
