package com.teambeme.beme.explore.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.ExploreDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityExploreDetailBinding
import com.teambeme.beme.databinding.ItemExploreDetailOtherAnswersBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.repository.ExploreRepositoryImpl
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.explore.viewmodel.ExploreViewModelFactory
import com.teambeme.beme.util.StatusBarUtil

class ExploreDetailActivity :
    BindingActivity<ActivityExploreDetailBinding>(R.layout.activity_explore_detail) {
    private val exploreDetailViewModelFactory = ExploreViewModelFactory(
        ExploreRepositoryImpl(
            ExploreDataSourceImpl(RetrofitObjects.getExploreService())
        )
    )
    private val exploreDetailViewModel: ExploreViewModel by viewModels { exploreDetailViewModelFactory }
    private var questionId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        binding.exploreDetailViewModel = exploreDetailViewModel
        questionId = intent.getIntExtra("questionId", 0)
        exploreDetailViewModel.requestSameQuestionsOtherAnswers(questionId)
        binding.lifecycleOwner = this
        binding.otherMindsTitle = intent.getStringExtra("otherMindsTitle")
        setOtherAnswersAdapter()
        setOtherAnswersObserve()
        setClickListenerForGoback()
        setTabSelectedFromExploreDetailListener()
        setIsMorePageObserve()
    }

    private fun setIsMorePageObserve() {
        exploreDetailViewModel.isMorePage.observe(this) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnExploreDetailShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnExploreDetailShowMore.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setOtherAnswersObserve() {
        exploreDetailViewModel.sameQuestionOtherAnswersList.observe(this) { otherAnswersList ->
            otherAnswersList?.let {
                if (binding.rcvExploreDetailOtherAnswers.adapter != null) with(binding.rcvExploreDetailOtherAnswers.adapter as OtherQuestionsRcvAdapter<*>) {
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
                exploreDetailViewModel,
                null
            )
        binding.rcvExploreDetailOtherAnswers.adapter = sameQuestionsOtherAnswersAdapter
    }

    private fun setClickListenerForGoback() {
        binding.btnExploreDetailGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setTabSelectedFromExploreDetailListener() {
        binding.tabLayoutExploreDetailSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            exploreDetailViewModel.setSortingTextFromExploreDetail(questionId, "최신")
                        }
                        1 -> {
                            exploreDetailViewModel.setSortingTextFromExploreDetail(questionId, "흥미")
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
        )
    }
}