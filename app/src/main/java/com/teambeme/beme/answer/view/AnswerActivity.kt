package com.teambeme.beme.answer.view

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.model.RequestAnswerData
import com.teambeme.beme.answer.viewmodel.AnswerViewModel
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.entity.AnswerData
import com.teambeme.beme.databinding.ActivityAnswerBinding
import com.teambeme.beme.util.StatusBarUtil
import com.teambeme.beme.util.dp
import com.teambeme.beme.util.recordClickEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnswerActivity : BindingActivity<ActivityAnswerBinding>(R.layout.activity_answer) {
    private val answerViewModel: AnswerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.answerActivity = this
        binding.answerViewModel = answerViewModel
        StatusBarUtil.setStatusBar(this, Color.WHITE)

        val intentAnswerData = intent.getParcelableExtra<IntentAnswerData>("intentAnswerData")!!
        val isChange = intent.getIntExtra(IS_CHANGE, IS_WRITE_VALUE)
        answerViewModel.setIntentAnswerData(intentAnswerData)
        Log.d("answer", intentAnswerData.toString())
        Log.d("answer", "isChange == IS_CHANGE_VALUE is ${isChange == IS_CHANGE_VALUE}")
        binding.txtAnswerData.text = intentAnswerData.createdAt
        if (isChange != IS_CHANGE_VALUE) {
            answerViewModel.checkStored(intentAnswerData.questionId)
        } else {
            answerViewModel.initAnswerData(intentAnswerData)
        }
        answerViewModel.answerData.observe(this) {
            if (it != null) {
                Log.d("answer", "it is not null")
                answerViewModel.initEditText()
                setTitleText(it)
            } else {
                Log.d("answer", "it is null")
                answerViewModel.initAnswerData(intentAnswerData)
            }
        }
        binding.txtAnswerComplete.setOnClickListener {
            submitAnswer(isChange)
        }
        setSwitchListener()
        observePublicSwitch()
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

    private fun submitAnswer(status: Int) {
        val requestAnswerData = RequestAnswerData(
            answerId = answerViewModel.answerData.value!!.answerId.toInt(),
            content = answerViewModel.answer.value ?: "",
            isPublic = answerViewModel.isPublic.value ?: false,
            isCommentBlocked = getIsCommentBlockedValue(answerViewModel.isPublic.value)
        )
        if (status == IS_WRITE_VALUE) {
            lifecycleScope.launch {
                answerViewModel.registerAnswer(requestAnswerData)
                delay(500)
                val position = intent.getIntExtra("position", -1)
                intent.putExtra("position", position)
                intent.putExtra("content", answerViewModel.answer.value)
                setResult(RESULT_OK, intent)
                finish()
            }
        } else if (status == IS_CHANGE_VALUE) {
            lifecycleScope.launch {
                answerViewModel.modifyAnswer(requestAnswerData)
                delay(500)
                finish()
            }
        }
    }

    private fun getIsCommentBlockedValue(isPublic: Boolean?): Boolean {
        return if (isPublic != null) {
            when (answerViewModel.isPublic.value!!) {
                true -> answerViewModel.isCommentBlocked
                else -> false
            }
        } else {
            false
        }
    }

    private fun observePublicSwitch() {
        answerViewModel.isPublic.observe(this) { isPublic ->
            val layoutParams =
                binding.linearAnswerPublic.layoutParams!! as ConstraintLayout.LayoutParams
            if (isPublic) {
                recordClickEvent("SWITCH", "OPEN_ANSWER_ANSWERVIEW")
                val animator = setValueChangeAnimator(20.dp, 64.dp)
                val alphaAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
                    duration = 400
                    start()
                }
                animator.addUpdateListener { updatedAnimation ->
                    layoutParams.setMargins(0, 0, 0, updatedAnimation.animatedValue as Int)
                    binding.linearAnswerPublic.layoutParams = layoutParams
                }
                alphaAnimator.addUpdateListener {
                    binding.linearAnswerBlockComment.alpha = it.animatedValue as Float
                }
            } else {
                recordClickEvent("SWITCH", "PRIVATE_ANSWER_ANSWERVIEW")
                val animator = setValueChangeAnimator(64.dp, 20.dp)
                animator.addUpdateListener { updatedAnimation ->
                    layoutParams.setMargins(0, 0, 0, updatedAnimation.animatedValue as Int)
                    binding.linearAnswerPublic.layoutParams = layoutParams
                }
            }
            binding.switchAnswerPublic.isChecked = isPublic
        }
    }

    private fun setSwitchListener() {
        binding.switchAnswerPublic.setOnCheckedChangeListener { _, isChecked ->
            answerViewModel.setPublicStatus(isChecked)
        }

        binding.switchAnswerReplyCancel.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) recordClickEvent("SWITCH", "OPEN_COMMENT_ANSWERVIEW")
            else recordClickEvent("SWITCH", "PRIVATE_COMMENT_ANSWERVIEW")
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

    companion object {
        const val IS_CHANGE_VALUE = 100
        const val IS_WRITE_VALUE = 0
        const val IS_CHANGE = "isChange"
    }
}