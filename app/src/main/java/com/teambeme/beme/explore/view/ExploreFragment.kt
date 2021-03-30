package com.teambeme.beme.explore.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.idsearchfollowing.view.FollowingAfterIdSearchActivity
import com.teambeme.beme.main.viewmodel.EventViewModel
import com.teambeme.beme.notification.view.NotificationActivity
import com.teambeme.beme.util.RecordScreenUtil
import com.teambeme.beme.util.recordClickEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    private val eventViewModel: EventViewModel by activityViewModels()
    override fun onResume() {
        super.onResume()
        RecordScreenUtil.recordScreen("ExploreFragment")
        exploreViewModel.requestOtherQuestionsWithCategorySorting(
            exploreViewModel.categoryNum,
            exploreViewModel.tempPage
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
        binding.exploreViewModel = exploreViewModel
        binding.lifecycleOwner = this
        setOtherQuestionsAdapter()
        setOtherQuestionsObserve()
        setClickListenerForIdSearchButton()
        setClickListenerForAlarmButton()
        setIsMorePageObserve()
        setIntentAnswerObserve()
        setListenerForPullRefreshLayout()
        setChipListener()
        RecordScreenUtil.recordScreen("ExploreFragment")
        setScrollToTop()
        return binding.root
    }

    private fun setChipListener() {
        with(binding) {
            chipExploreThink.setOnClickListener {
                recordClickEvent(
                    "BUTTON",
                    "CLICK_VALUES_SEARCH"
                )
            }
            chipExploreRelationship.setOnClickListener {
                recordClickEvent(
                    "BUTTON",
                    "CLICK_RELATIONSHIP_SEARCH"
                )
            }
            chipExploreLove.setOnClickListener { recordClickEvent("BUTTON", "CLICK_LOVE_SEARCH") }
            chipExploreDaily.setOnClickListener {
                recordClickEvent(
                    "BUTTON",
                    "CLICK_DAILYLIFE_SEARCH"
                )
            }
            chipExploreMe.setOnClickListener { recordClickEvent("BUTTON", "CLICK_ABOUTME_SEARCH") }
            chipExploreStory.setOnClickListener { recordClickEvent("BUTTON", "CLICK_STORY_SEARCH") }
        }
    }

    private fun setIntentAnswerObserve() {
        exploreViewModel.questionForFirstAnswer.observe(viewLifecycleOwner) {
            it?.let {
                val intentAnswerData = IntentAnswerData(
                    it.questionId,
                    it.id,
                    it.questionTitle,
                    it.questionCategoryName,
                    it.answerIdx,
                    transformDateFormat(it.createdAt)
                )
                val intent = Intent(context, AnswerActivity::class.java)
                intent.putExtra("intentAnswerData", intentAnswerData)
                startActivity(intent)
            }
        }
    }

    private fun setIsMorePageObserve() {
        exploreViewModel.isMorePage.observe(viewLifecycleOwner) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnExploreShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnExploreShowMore.visibility = View.GONE
                }
            }
        }
    }

    private fun setOtherQuestionsAdapter() {
        val otherQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(),
                R.layout.item_explore_other_questions,
                exploreViewModel.userNickname,
                exploreViewModel
            )
        binding.rcvExploreOtherQuestions.adapter = otherQuestionsAdapter
    }

    private fun setOtherQuestionsObserve() {
        exploreViewModel.otherQuestionsList.observe(viewLifecycleOwner) { otherQuestionsList ->
            otherQuestionsList?.let {
                if (binding.rcvExploreOtherQuestions.adapter != null) with(binding.rcvExploreOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherQuestionsList)
                }
            }
        }
    }

    private fun setClickListenerForIdSearchButton() {
        binding.btnExploreIdSearch.setOnClickListener {
            recordClickEvent("BUTTON", "CLICK_SEARCHID_SEARCH")
            val intent = Intent(activity, FollowingAfterIdSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setClickListenerForAlarmButton() {
        binding.btnExploreAlarm.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun transformDateFormat(date: String): String {
        return if (date.length > HomeFragment.DATE_LENGTH)
            date.substring(0, HomeFragment.DATE_LENGTH)
        else
            date
    }

    private fun setListenerForPullRefreshLayout() {
        binding.pullRefreshLayoutExplore.setOnRefreshListener {
            exploreViewModel.setPageAtRefresh()
            exploreViewModel.requestOtherQuestionsWithCategorySorting(
                exploreViewModel.categoryNum,
                exploreViewModel.tempPage
            )
            binding.pullRefreshLayoutExplore.setRefreshing(false)
        }
    }

    private fun setScrollToTop() {
        eventViewModel.secondButtonClicked.observe(viewLifecycleOwner) {
            binding.stickyScrollViewExplore.apply { smoothScrollTo(0, this.top) }
        }
    }
}