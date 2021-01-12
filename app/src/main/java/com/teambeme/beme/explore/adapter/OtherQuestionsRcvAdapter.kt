package com.teambeme.beme.explore.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.BR
import com.teambeme.beme.R
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.util.startActivity

class OtherQuestionsRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int,
    private val viewModel: ViewModel
) :
    ListAdapter<ResponseExplorationQuestions.Data.Answer, OtherQuestionsRcvAdapter<B>.OtherQuestionsRcvViewHolder<B>>(
        OtherQuestionsDiffUtil()
    ) {
    inner class OtherQuestionsRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherQuestionsData: ResponseExplorationQuestions.Data.Answer) {
            when (binding) {
                is ItemExploreOtherQuestionsBinding -> {
                    with(binding as ItemExploreOtherQuestionsBinding) {
                        Log.d(
                            "network_1",
                            otherQuestionsData.toString()
                        )
                        setVariable(BR.otherQuestions, otherQuestionsData)
                        executePendingBindings()
                        setClickListenerForQuestionsBookmark(binding, otherQuestionsData)
                        setClickListenerForShowOtherAnswers(binding, otherQuestionsData, context)
                        setClickListenerForBtnDoAnswer(binding, otherQuestionsData)
                    }
                }
                else -> {
                    with(binding as ItemExploreDetailOtherAnswersBinding) {
                        setVariable(BR.otherAnswers, otherQuestionsData)
                        executePendingBindings()
                        setClickListenerForAnswersBookmark(binding, otherQuestionsData)
                        Log.d(
                            "network_2",
                            otherQuestionsData.toString()
                        )
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

    private class OtherQuestionsDiffUtil :
        DiffUtil.ItemCallback<ResponseExplorationQuestions.Data.Answer>() {
        override fun areItemsTheSame(
            oldItem: ResponseExplorationQuestions.Data.Answer,
            newItem: ResponseExplorationQuestions.Data.Answer
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseExplorationQuestions.Data.Answer,
            newItem: ResponseExplorationQuestions.Data.Answer
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForShowOtherAnswers(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: ResponseExplorationQuestions.Data.Answer,
        context: Context
    ) {
        binding.btnOtherQuestionsShowOtherAnswers.setOnClickListener {
            otherQuestionsData.question?.let { title ->
                context.startActivity<ExploreDetailActivity>(title, otherQuestionsData.questionId)
            }
        }
    }

    private fun setClickListenerForQuestionsBookmark(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: ResponseExplorationQuestions.Data.Answer
    ) {
        binding.btnOtherQuestionsBookmark.setOnClickListener {
            when (viewModel) {
                is ExploreViewModel -> {
                    viewModel.requestScrap(otherQuestionsData.id, otherQuestionsData)
                    otherQuestionsData.isScrapped = !otherQuestionsData.isScrapped
                    if (otherQuestionsData.isScrapped) {
                        binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                    } else {
                        binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark)
                    }
                    Log.d("scrap", otherQuestionsData.isScrapped.toString())
                }
            }
        }
    }

    private fun setClickListenerForAnswersBookmark(
        binding: ItemExploreDetailOtherAnswersBinding,
        otherAnswersData: ResponseExplorationQuestions.Data.Answer
    ) {
        binding.btnOtherAnswersBookmark.setOnClickListener {
            when (viewModel) {
                is ExploreViewModel -> {
                    viewModel.requestScrap(otherAnswersData.id, otherAnswersData)
                    otherAnswersData.isScrapped = !otherAnswersData.isScrapped
                    if (otherAnswersData.isScrapped) {
                        binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark_checked)
                    } else {
                        binding.btnOtherAnswersBookmark.setImageResource(R.drawable.ic_bookmark)
                    }
                }
            }
        }
    }

    private fun setClickListenerForBtnDoAnswer(
        binding: ItemExploreOtherQuestionsBinding,
        otherQuestionsData: ResponseExplorationQuestions.Data.Answer
    ) {
        binding.btnOtherQuestionsDoAnswer.setOnClickListener {
            val intent = Intent(context, AnswerActivity::class.java)
            context.startActivity(intent)
        }
    }
}
