package com.teambeme.beme.explore.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityExploreDetailBinding
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreDetailActivity :
    BindingActivity<ActivityExploreDetailBinding>(R.layout.activity_explore_detail) {
    private val exploreDetailViewModel: ExploreViewModel by viewModels()
    private var questionId: Int = 0

    override fun onRestart() {
        super.onRestart()
        exploreDetailViewModel.requestSameQuestionsOtherAnswers(
            questionId,
            exploreDetailViewModel.tempPage
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        binding.exploreDetailViewModel = exploreDetailViewModel
        questionId = intent.getIntExtra("questionId", 0)
        exploreDetailViewModel.requestSameQuestionsOtherAnswers(
            questionId,
            exploreDetailViewModel.tempPage
        )
        binding.lifecycleOwner = this
        binding.otherMindsTitle = intent.getStringExtra("otherMindsTitle")
        setOtherAnswersAdapter()
        setOtherAnswersObserve()
        setClickListenerForGoback()
        setIsMorePageObserve()
    }

    private fun setIsMorePageObserve() {
        exploreDetailViewModel.isMorePage.observe(this) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnExploreDetailShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnExploreDetailShowMore.visibility = View.GONE
                }
            }
        }
    }

    private fun setOtherAnswersObserve() {
        exploreDetailViewModel.sameQuestionOtherAnswersList.observe(this) { otherAnswersList ->
            otherAnswersList?.let {
                if (binding.rcvExploreDetailOtherAnswers.adapter != null)
                    with(binding.rcvExploreDetailOtherAnswers.adapter as OtherQuestionsRcvAdapter<*>) {
                        submitList(otherAnswersList)
                    }
                if (otherAnswersList.size == 0) {
                    binding.constraintLayoutExploreDetailEmpty.visibility = View.VISIBLE
                } else {
                    binding.constraintLayoutExploreDetailEmpty.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setOtherAnswersAdapter() {
        val sameQuestionsOtherAnswersAdapter =
            OtherQuestionsRcvAdapter<ItemExploreDetailOtherAnswersBinding>(
                this,
                R.layout.item_explore_detail_other_answers,
                exploreDetailViewModel.userNickname,
                exploreDetailViewModel
            )
        binding.rcvExploreDetailOtherAnswers.adapter = sameQuestionsOtherAnswersAdapter
    }

    private fun setClickListenerForGoback() {
        binding.btnExploreDetailGoBack.setOnClickListener {
            onBackPressed()
        }
    }
}
