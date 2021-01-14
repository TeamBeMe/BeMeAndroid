package com.teambeme.beme.answer.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.repository.AnswerRepositoryImpl
import com.teambeme.beme.answer.viewmodel.AnswerViewModel
import com.teambeme.beme.answer.viewmodel.AnswerViewModelFactory
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.database.AppDatabase
import com.teambeme.beme.data.local.entity.AnswerData
import com.teambeme.beme.data.remote.datasource.AnswerDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityAnswerBinding
import com.teambeme.beme.util.StatusBarUtil
import com.teambeme.beme.util.dp

class AnswerActivity : BindingActivity<ActivityAnswerBinding>(R.layout.activity_answer) {

    private lateinit var answerViewModel: AnswerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.answerActivity = this
        initViewModel(this)
        binding.answerViewModel = answerViewModel
        StatusBarUtil.setStatusBar(this, Color.WHITE)

        val intentAnswerData = intent.getParcelableExtra<IntentAnswerData>("intentAnswerData")!!
        answerViewModel.checkStored(intentAnswerData.questionId)
        answerViewModel.answerData.observe(this) {
            if (it != null) {
                answerViewModel.initEditText()
                setTitleText(it)
            } else {
                answerViewModel.initAnswerData(intentAnswerData)
            }
        }
        setSwitchListener()
        observePublicSwitch()
    }

    private fun initViewModel(context: Context) {
        val answerViewModelFactory = AnswerViewModelFactory(
            AnswerRepositoryImpl(
                AppDatabase.getInstance(context.applicationContext).answerDao,
                AnswerDataSourceImpl(RetrofitObjects.getAnswerService())
            )
        )

        answerViewModel =
            ViewModelProvider(this, answerViewModelFactory).get(AnswerViewModel::class.java)
    }

    private fun setTitleText(answerData: AnswerData) {
        val text = "[ " + answerData.category + "에 관한 " + answerData.categoryIdx + "번째 질문 ]"
        val digit = answerData.categoryIdx.toString().length
        val spannableString = SpannableStringBuilder(text)
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            7 + answerData.category.length,
            9 + answerData.category.length + digit,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            UnderlineSpan(),
            7 + answerData.category.length,
            9 + answerData.category.length + digit,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.txtAnswerInfo.text = spannableString
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun setClickText() {
        Toast.makeText(this, "클릭가능", Toast.LENGTH_SHORT).show()
    }

    private fun observePublicSwitch() {
        answerViewModel.isPublic.observe(this) { isPublic ->
            val layoutParams =
                binding.linearAnswerPublic.layoutParams!! as ConstraintLayout.LayoutParams
            if (isPublic) {
                val animator = setValueChangeAnimator(20.dp, 64.dp)
                animator.addUpdateListener { updatedAnimation ->
                    layoutParams.setMargins(0, 0, 0, updatedAnimation.animatedValue as Int)
                    binding.linearAnswerPublic.layoutParams = layoutParams
                }
            } else {
                val animator = setValueChangeAnimator(64.dp, 20.dp)
                animator.addUpdateListener { updatedAnimation ->
                    layoutParams.setMargins(0, 0, 0, updatedAnimation.animatedValue as Int)
                    binding.linearAnswerPublic.layoutParams = layoutParams
                }
            }
        }
    }

    private fun setSwitchListener() {
        binding.switchAnswerPublic.setOnCheckedChangeListener { _, isChecked ->
            answerViewModel.setPublicStatus(isChecked)
        }

        binding.switchAnswerReplyCancel.setOnCheckedChangeListener { _, isChecked ->
            answerViewModel.setCommentBlockedStatus(isChecked)
        }
    }

    private fun setValueChangeAnimator(from: Int, to: Int): ValueAnimator {
        return ValueAnimator.ofInt(from, to).apply {
            duration = 400
            start()
        }
    }

    override fun onPause() {
        super.onPause()
        answerViewModel.storeAnswer()
    }
}