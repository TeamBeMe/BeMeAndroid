package com.teambeme.beme.explore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
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
        setOtherMindsAdapter(binding)
        setOtherQuestionsAdapter(binding)
        setOtherMindsObserve(binding)
        setOtherQuestionsObserve(binding)
        setTabSelectedListener(binding)
        setSnapHelper(binding)
        return binding.root
    }

    private fun setOtherMindsAdapter(binding: FragmentExploreBinding) {
        val otherMindsAdapter = OtherMindsRcvAdapter(requireContext())
        binding.rcvExploreOtherMinds.adapter = otherMindsAdapter
    }

    private fun setOtherQuestionsAdapter(binding: FragmentExploreBinding) {
        val otherQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(requireContext(), R.layout.item_explore_other_questions)
        binding.rcvExploreOtherQuestions.adapter = otherQuestionsAdapter
    }

    private fun setOtherMindsObserve(binding: FragmentExploreBinding) {
        exploreViewModel.otherMindsList.observe(viewLifecycleOwner) { othermindsList ->
            othermindsList?.let {
                if (binding.rcvExploreOtherMinds.adapter != null) with(binding.rcvExploreOtherMinds.adapter as OtherMindsRcvAdapter) {
                    submitList(othermindsList)
                }
            }
        }
    }

    private fun setOtherQuestionsObserve(binding: FragmentExploreBinding) {
        exploreViewModel.otherQuestionsList.observe(viewLifecycleOwner) { otherquestionsList ->
            otherquestionsList?.let {
                if (binding.rcvExploreOtherQuestions.adapter != null) with(binding.rcvExploreOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherquestionsList)
                }
            }
        }
    }

    private fun setTabSelectedListener(binding: FragmentExploreBinding) {
        binding.tablayoutExploreSort.addOnTabSelectedListener(object :
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

    private fun setSnapHelper(binding: FragmentExploreBinding) {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcvExploreOtherMinds)
    }
}