package com.teambeme.beme.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentHomeBinding
import com.teambeme.beme.home.adapter.QuestionPagerAdapter
import com.teambeme.beme.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val questionPagerAdapter = QuestionPagerAdapter()
        binding.vpHomeQuestionSlider.apply {
            adapter = questionPagerAdapter
        }

        homeViewModel.setDummyQuestions()
        homeViewModel.questionList.observe(viewLifecycleOwner) { questionList ->
            questionPagerAdapter.replaceQuestionList(questionList.toList())
        }

        return binding.root
    }
}