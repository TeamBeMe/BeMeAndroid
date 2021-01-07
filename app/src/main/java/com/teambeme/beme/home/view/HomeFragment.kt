package com.teambeme.beme.home.view

import android.annotation.SuppressLint
import android.graphics.Color
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
        binding.lifecycleOwner?.lifecycle?.let { lifecycle -> LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle) }
        val compositePageTransformer = getPageTransformer()
        val questionPagerAdapter = QuestionPagerAdapter()

        binding.vpHomeQuestionSlider.apply {
            adapter = questionPagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 4
            setPageTransformer(compositePageTransformer)
            setPadding(80, 0, 80, 0)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        homeViewModel.setDummyQuestions()
        homeViewModel.questionList.observe(viewLifecycleOwner) { questionList ->
            questionPagerAdapter.replaceQuestionList(questionList.toList())
        }

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

    @SuppressLint("InlinedApi")
    @Suppress("DEPRECATION")
    private fun setStatusBarColor() {
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = Color.BLACK
    }
}