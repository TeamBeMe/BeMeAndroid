package com.teambeme.beme.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentHomeBinding
import com.teambeme.beme.home.adapter.QuestionPagerAdapter
import com.teambeme.beme.home.viewmodel.HomeViewModel
import kotlin.math.abs

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner?.lifecycle?.let { lifecycle ->
            LifeCycleEventLogger(javaClass.name).registerLogger(
                lifecycle
            )
        }
        val compositePageTransformer = getPageTransformer()
        val questionPagerAdapter = QuestionPagerAdapter(childFragmentManager)

        binding.vpHomeQuestionSlider.apply {
            adapter = questionPagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 4
            setPageTransformer(compositePageTransformer)
            setPadding(120, 0, 120, 0)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        homeViewModel.setDummyQuestions()
        homeViewModel.questionList.observe(viewLifecycleOwner) { questionList ->
            questionPagerAdapter.replaceQuestionList(questionList.toList())
            binding.vpHomeQuestionSlider.setCurrentItem(questionList.size - 1, false)
        }

        binding.vpHomeQuestionSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != 0) {
                    when (homeViewModel.questionList.value?.get(position - 1)?.isToday) {
                        true -> binding.txtHomeTitle.text = "오늘의 질문"
                        else -> binding.txtHomeTitle.text = "과거의 질문"
                    }
                }
            }
        })
        return binding.root
    }

    private fun getPageTransformer(): ViewPager2.PageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val scaleRatio = 1 - abs(position)
            page.scaleY = 0.95f + scaleRatio * 0.05f
        }
        return compositePageTransformer
    }

    fun returnToDefaultPosition() {
        binding.vpHomeQuestionSlider.post {
            homeViewModel.questionList
                .value
                ?.size
                ?.minus(1)
                ?.let { binding.vpHomeQuestionSlider.setCurrentItem(it, true) }
        }
    }
}