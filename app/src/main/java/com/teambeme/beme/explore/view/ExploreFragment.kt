package com.teambeme.beme.explore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.explore.adapter.OthermindsRcvAdapter
import com.teambeme.beme.explore.adapter.OtherquestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        binding.exploreViewModel = exploreViewModel
        exploreViewModel.setDummyOtherminds()
        exploreViewModel.setDummyOtherquestions()
        setAdapter(binding)
        setObserve(binding)
        setTabSelectedListener(binding)
        setSnapHelper(binding)
        return binding.root
    }

    private fun setAdapter(binding: FragmentExploreBinding) {
        val othermindsAdapter = OthermindsRcvAdapter()
        val otherquestionsAdapter = OtherquestionsRcvAdapter()
        binding.rcvExploreOtherminds.adapter = othermindsAdapter
        binding.rcvExploreOtherquestions.adapter = otherquestionsAdapter
    }

    private fun setObserve(binding: FragmentExploreBinding) {
        exploreViewModel.othermindsList.observe(viewLifecycleOwner, { othermindsList ->
            othermindsList?.let {
                if (binding.rcvExploreOtherminds.adapter != null) with(binding.rcvExploreOtherminds.adapter as OthermindsRcvAdapter) {
                    submitList(othermindsList)
                }
            }
        })
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
        snapHelper.attachToRecyclerView(binding.rcvExploreOtherminds)
    }
}