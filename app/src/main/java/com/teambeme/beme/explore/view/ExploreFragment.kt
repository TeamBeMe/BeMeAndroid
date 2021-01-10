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
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.explore.adapter.OtherMindsRcvAdapter
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {
    private val exploreViewModel: ExploreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
        binding.exploreViewModel = exploreViewModel
        binding.lifecycleOwner = this
        exploreViewModel.setDummyOtherMinds()
        exploreViewModel.setDummyOtherQuestions()
        setOtherMindsAdapter()
        setOtherQuestionsAdapter()
        setOtherMindsObserve()
        setOtherQuestionsObserve()
        setTabSelectedFromExploreListener()
        setSnapHelper()
        setClickListenerForExploreBtnDoAnswer()
        return binding.root
    }

    private fun setOtherMindsAdapter() {
        val otherMindsAdapter = OtherMindsRcvAdapter(requireContext())
        binding.rcvExploreOtherMinds.adapter = otherMindsAdapter
    }

    private fun setOtherQuestionsAdapter() {
        val otherQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(),
                R.layout.item_explore_other_questions
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
                            binding.txtExploreDescOfRecent.visibility = View.VISIBLE
                            binding.txtExploreDescOfExciting.visibility = View.INVISIBLE
                        }
                        1 -> {
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

    private fun setClickListenerForExploreBtnDoAnswer() {
        binding.btnExploreDoAnswer.setOnClickListener {
            val intent = Intent(context, AnswerActivity::class.java)
            startActivity(intent)
        }
    }
}