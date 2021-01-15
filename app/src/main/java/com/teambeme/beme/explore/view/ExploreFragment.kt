package com.teambeme.beme.explore.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.data.remote.datasource.ExploreDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.explore.adapter.OtherMindsRcvAdapter
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.repository.ExploreRepositoryImpl
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.explore.viewmodel.ExploreViewModelFactory
import com.teambeme.beme.idsearchfollowing.view.FollowingAfterIdSearchActivity
import com.teambeme.beme.notification.view.NotificationActivity

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    private val exploreViewModelFactory = ExploreViewModelFactory(
        ExploreRepositoryImpl(
            ExploreDataSourceImpl(RetrofitObjects.getExploreService())
        )
    )
    private val exploreViewModel: ExploreViewModel by activityViewModels { exploreViewModelFactory }

    override fun onResume() {
        exploreViewModel.requestOtherMinds()
        super.onResume()
        exploreViewModel.requestOtherQuestionsWithCategorySorting(
            exploreViewModel.categoryNum,
            exploreViewModel.sortingText,
            1
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
        exploreViewModel.requestOtherMinds()
        exploreViewModel.requestOtherQuestions()
        setOtherMindsAdapter()
        setOtherQuestionsAdapter()
        setOtherMindsObserve()
        setOtherQuestionsObserve()
        setTabSelectedFromExploreListener()
        setSnapHelper()
        setClickListenerForExploreBtnDoAnswer()
        setClickListenerForIdSearchButton()
        setClickListenerForAlarmButton()
        setIsMorePageObserve()
        setIntentAnswerObserve()
        return binding.root
    }

    private fun setIntentAnswerObserve() {
        exploreViewModel.questionForFirstAnswer.observe(viewLifecycleOwner) {
            it?.let {
                val intentAnswerData = IntentAnswerData(
                    it.id,
                    it.questionTitle,
                    it.questionCategoryName,
                    it.answerIdx,
                    it.createdAt
                )
                val intent = Intent(context, AnswerActivity::class.java)
                intent.putExtra("intentAnswerData", intentAnswerData)
                startActivity(intent)
            }
        }
    }

    private fun setClickListenerForExploreBtnDoAnswer() {
        binding.btnExploreDoAnswer.setOnClickListener {
            exploreViewModel.requestQuestionForFirstAnswer()
        }
    }

    private fun setIsMorePageObserve() {
        exploreViewModel.isMorePage.observe(viewLifecycleOwner) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnExploreShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnExploreShowMore.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setOtherMindsAdapter() {
        val otherMindsAdapter = OtherMindsRcvAdapter(requireContext())
        binding.rcvExploreOtherMinds.adapter = otherMindsAdapter
    }

    private fun setOtherQuestionsAdapter() {
        val otherQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(),
                R.layout.item_explore_other_questions,
                exploreViewModel.userNickname,
                exploreViewModel,
                null
            )
        binding.rcvExploreOtherQuestions.adapter = otherQuestionsAdapter
    }

    private fun setOtherMindsObserve() {
        exploreViewModel.otherMindsList.observe(viewLifecycleOwner) { otherMindsList ->
            otherMindsList?.let {
                if (binding.rcvExploreOtherMinds.adapter != null) with(binding.rcvExploreOtherMinds.adapter as OtherMindsRcvAdapter) {
                    submitList(otherMindsList)
                }
            }
        }
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

    private fun setTabSelectedFromExploreListener() {
        binding.tabLayoutExploreSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            exploreViewModel.setSortingTextFromExplore("최신")
                            binding.txtExploreDescOfRecent.visibility = View.VISIBLE
                            binding.txtExploreDescOfExciting.visibility = View.INVISIBLE
                        }
                        1 -> {
                            exploreViewModel.setSortingTextFromExplore("흥미")
                            binding.txtExploreDescOfRecent.visibility = View.INVISIBLE
                            binding.txtExploreDescOfExciting.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
        )
    }

    private fun setSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcvExploreOtherMinds)
    }

    private fun setClickListenerForIdSearchButton() {
        binding.btnExploreIdSearch.setOnClickListener {
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
}