package com.teambeme.beme.home.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentHomeBinding
import com.teambeme.beme.home.adapter.QuestionPagerAdapter
import com.teambeme.beme.home.model.Answer
import com.teambeme.beme.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
        val questionPagerAdapter =
            QuestionPagerAdapter(childFragmentManager, homeViewModel, getHomeButtonClickListener())
        setAnswerPager(questionPagerAdapter)
        setObserve()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.refreshTaskCompleted()
        returnToDefaultPosition()
    }

    private fun getHomeButtonClickListener(): QuestionPagerAdapter.QuestionButtonClickListener {
        return object : QuestionPagerAdapter.QuestionButtonClickListener {
            override fun onAnswerButtonClick(answer: Answer, position: Int) {
                val intent = Intent(context, AnswerActivity::class.java)
                val intentData = IntentAnswerData(
                    questionId = answer.questionId,
                    answerId = answer.id,
                    title = answer.questionTitle,
                    category = answer.questionCategoryName,
                    categoryIdx = answer.answerIdx?.toInt(),
                    createdAt = transformDateFormat(answer.createdAt)
                )
                intent.putExtra("intentAnswerData", intentData)
                intent.putExtra("position", position)
                startActivityForResult(intent, ANSWER_ACTIVITY)
            }
        }
    }

    private fun setObserve() {
        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        homeViewModel.returnToStartEvent.observe(viewLifecycleOwner) {
            if (it) {
                returnToDefaultPosition()
                homeViewModel.setReadyToReceiveEvent()
            }
        }
        homeViewModel.successMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAnswerPager(pagerAdapter: QuestionPagerAdapter) {
        val compositePageTransformer = getPageTransformer()
        binding.vpHomeQuestionSlider.apply {
            adapter = pagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            setPageTransformer(compositePageTransformer)
            setPadding(120, 0, 120, 0)
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        homeViewModel.answerList.observe(viewLifecycleOwner) {
            pagerAdapter.replaceQuestionList(it.toList())
        }

        binding.vpHomeQuestionSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != 0 && position != homeViewModel.answerList.value?.size?.plus(1)) {
                    when (homeViewModel.answerList.value?.get(position - 1)?.isToday) {
                        true -> binding.txtHomeTitle.text = "오늘의 질문"
                        else -> binding.txtHomeTitle.text = "과거의 질문"
                    }
                } else if (position == homeViewModel.answerList.value?.size?.plus(1)) {
                    binding.txtHomeTitle.text = "새로운 질문"
                } else {
                    binding.txtHomeTitle.text = "과거의 질문 더 보기"
                }
            }
        })
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
        binding.vpHomeQuestionSlider.postDelayed({
            homeViewModel.answerList
                .value
                ?.size
                ?.let { binding.vpHomeQuestionSlider.setCurrentItem(it, true) }
        }, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ANSWER_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                val position = data!!.getIntExtra("position", -1)
                val answerList = homeViewModel.answerList.value!!.toMutableList()
                answerList[position].content = data!!.getStringExtra("content")
                homeViewModel.refreshList(answerList)
            }
        }
    }

    private fun transformDateFormat(date: String): String {
        return if (date.length > DATE_LENGTH)
            date.substring(0, DATE_LENGTH)
        else
            date
    }

    companion object {
        const val ANSWER_ACTIVITY = 1000
        const val DATE_LENGTH = 10
    }
}