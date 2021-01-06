package com.teambeme.beme.explore.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityExploreDetailBinding
import com.teambeme.beme.databinding.ListExploredetailOtheranswersBinding
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
        exploreDetailViewModel.otherAnswersList.observe(this) { otheranswersList ->
            otheranswersList?.let {
                if (binding.rcvExploredetailOtheranswers.adapter != null) with(binding.rcvExploredetailOtheranswers.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otheranswersList)
                }
            }
        }
    }

    private fun setOtherAnswersAdapter(binding: ActivityExploreDetailBinding) {
        val otherAnswersAdapter =
            OtherQuestionsRcvAdapter<ListExploredetailOtheranswersBinding>(this, R.layout.list_exploredetail_otheranswers)
        binding.rcvExploredetailOtheranswers.adapter = otherAnswersAdapter
    }

    private fun setClickListenerForGoback(binding: ActivityExploreDetailBinding) {
        binding.btnExploredetailGoback.setOnClickListener {
            onBackPressed()
        }
    }
}