package com.teambeme.beme.explore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentExploreBinding
import com.teambeme.beme.explore.adapter.OthermindsRcvAdapter
import com.teambeme.beme.explore.adapter.OtherquestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {
    private val exploreViewModel: ExploreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExploreBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        binding.exploreViewModel = exploreViewModel
        exploreViewModel.setDummyOtherminds()
        exploreViewModel.setDummyOtherquestions()
        setAdapter(binding)
        return binding.root
    }

    private fun setAdapter(binding: FragmentExploreBinding) {
        val othermindsAdapter = OthermindsRcvAdapter()
        val otherquestionsAdapter = OtherquestionsRcvAdapter()
        binding.rcvExploreOtherminds.adapter = othermindsAdapter
        binding.rcvExploreOtherquestions.adapter = otherquestionsAdapter
    }
}