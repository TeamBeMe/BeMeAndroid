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
import com.teambeme.beme.data.remote.datasource.ExploreDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.repository.ExploreRepositoryImpl
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.explore.viewmodel.ExploreViewModelFactory
import com.teambeme.beme.home.view.HomeFragment
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
        super.onResume()
        exploreViewModel.requestOtherQuestionsWithCategorySorting(
            exploreViewModel.categoryNum,
            exploreViewModel.sortingText,
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
        return binding.root
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
                exploreViewModel.sortingText,
                exploreViewModel.tempPage
            )
            binding.pullRefreshLayoutExplore.setRefreshing(false)
        }
    }
}