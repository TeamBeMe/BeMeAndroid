package com.teambeme.beme.explore.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityExploreDetailBinding
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.util.StatusBarUtil

class ExploreDetailActivity :
    BindingActivity<ActivityExploreDetailBinding>(R.layout.activity_explore_detail) {
    private val exploreDetailViewModel: ExploreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        binding.exploreDetailViewModel = exploreDetailViewModel
        binding.lifecycleOwner = this
        binding.otherMindsTitle = intent.getStringExtra("otherMindsTitle")
        setOtherAnswersAdapter(binding)
        setOtherAnswersObserve(binding)
        exploreDetailViewModel.setDummyOtherAnswers()
        setClickListenerForGoback(binding)
    }

    private fun setOtherAnswersObserve(binding: ActivityExploreDetailBinding) {
        exploreDetailViewModel.otherAnswersList.observe(this) { otherAnswersList ->
            otherAnswersList?.let {
                if (binding.rcvExploreDetailOtherAnswers.adapter != null) with(binding.rcvExploreDetailOtherAnswers.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherAnswersList)
                }
            }
        }
    }

    private fun setOtherAnswersAdapter(binding: ActivityExploreDetailBinding) {
        val otherAnswersAdapter =
            OtherQuestionsRcvAdapter<ItemExploreDetailOtherAnswersBinding>(this, R.layout.item_explore_detail_other_answers)
        binding.rcvExploreDetailOtherAnswers.adapter = otherAnswersAdapter
    }

    private fun setClickListenerForGoback(binding: ActivityExploreDetailBinding) {
        binding.btnExploreDetailGoBack.setOnClickListener {
            onBackPressed()
        }
    }
}