package com.teambeme.beme.explore.adapter

import android.content.Context
import android.content.Intent
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
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.view.ExploreDetailActivity
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity
import com.teambeme.beme.util.startActivity

class OtherQuestionsRcvAdapter<B : ViewDataBinding>(
    private val context: Context,
    private val layout: Int,
    private val myNickname: String,
    private val viewModel: ViewModel
) :
    ListAdapter<ResponseExplorationQuestions.Data.Answer, OtherQuestionsRcvAdapter<B>.OtherQuestionsRcvViewHolder>(
        OtherQuestionsDiffUtil()
    ) {
    inner class OtherQuestionsRcvViewHolder(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherQuestionsData: ResponseExplorationQuestions.Data.Answer) {
            when (binding) {
                is ItemExploreOtherQuestionsBinding -> {
                    with(binding as ItemExploreOtherQuestionsBinding) {
                        setVariable(BR.myNickName, myNickname)
                        setVariable(BR.userNickName, otherQuestionsData.userNickname)
                        setVariable(BR.otherQuestions, otherQuestionsData)
                        executePendingBindings()
                        setClickListenerForQuestionsBookmark(binding, otherQuestionsData)
                        setClickListenerForShowOtherAnswers(binding, otherQuestionsData, context)
                        setClickListenerForGoProfilePageFromFragment(
                            binding,
                            otherQuestionsData,
                            context
                        )
                        setClickListenerForGoDetailFromFragment(
                            binding,
                            otherQuestionsData,
                            context
                        )
                    }
                }
                else -> {
                    with(binding as ItemExploreDetailOtherAnswersBinding) {
                        setVariable(BR.otherAnswers, otherQuestionsData)
                        executePendingBindings()
                        setClickListenerForAnswersBookmark(binding, otherQuestionsData)
                        setClickListenerForGoProfilePageFromActivity(
                            binding,
                            otherQuestionsData,
                            context
                        )
                        setClickListenerForGoDetailFromActivity(
                            binding,
                            otherQuestionsData,
                            context
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherQuestionsRcvViewHolder {
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

    override fun onBindViewHolder(holder: OtherQuestionsRcvViewHolder, position: Int) {
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
                    viewModel.requestScrap(otherQuestionsData.id)
                }
                is FollowingViewModel -> {
                    viewModel.requestScrap(otherQuestionsData.id)
                }
            }
            otherQuestionsData.isScrapped = !otherQuestionsData.isScrapped
            if (otherQuestionsData.isScrapped) {
                binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark_checked)
            } else {
                binding.btnOtherQuestionsBookmark.setImageResource(R.drawable.ic_bookmark)
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
                    viewModel.requestScrap(otherAnswersData.id)
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

    private fun setClickListenerForGoProfilePageFromFragment(
        binding: ItemExploreOtherQuestionsBinding,
        data: ResponseExplorationQuestions.Data.Answer,
        context: Context
    ) {
        binding.imgOtherQuestionsProfile.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.userId)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoProfilePageFromActivity(
        binding: ItemExploreDetailOtherAnswersBinding,
        data: ResponseExplorationQuestions.Data.Answer,
        context: Context
    ) {
        binding.imgOtherAnswersProfile.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", data.userId)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoDetailFromFragment(
        binding: ItemExploreOtherQuestionsBinding,
        data: ResponseExplorationQuestions.Data.Answer,
        context: Context
    ) {
        binding.constraintLayoutOtherQuestions.setOnClickListener {
//            if (data.isAnswered) {
//                // 답변하고 확인하기 적용 시 밑에 3줄 여기로
//            }
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", data.id)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoDetailFromActivity(
        binding: ItemExploreDetailOtherAnswersBinding,
        data: ResponseExplorationQuestions.Data.Answer,
        context: Context
    ) {
        binding.constraintLayoutOtherAnswers.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", data.id)
            intent.putExtra("deleteBtnOtherAnswers", true)
            context.startActivity(intent)
        }
    }
}
