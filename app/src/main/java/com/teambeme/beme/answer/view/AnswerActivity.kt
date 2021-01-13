package com.teambeme.beme.answer.view

import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.R
import com.teambeme.beme.answer.viewmodel.AnswerViewModel
import com.teambeme.beme.answer.viewmodel.AnswerViewModelFactory
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.database.AppDatabase
import com.teambeme.beme.databinding.ActivityAnswerBinding
import com.teambeme.beme.util.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnswerActivity : BindingActivity<ActivityAnswerBinding>(R.layout.activity_answer) {
    private var viewJob = Job()
    private lateinit var answerViewModel: AnswerViewModel
    private val uiScope = CoroutineScope(Dispatchers.Main + viewJob)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.answerActivity = this
        initViewModel()
        val id = intent.getIntExtra("id", 0)
        initEditText(id)
        setSwitchListener()
        observePublicSwitch()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun initViewModel() {
        val answerViewModelFactory =
            AnswerViewModelFactory(AppDatabase.getInstance(applicationContext).answerDao)
        answerViewModel =
            ViewModelProvider(this, answerViewModelFactory).get(AnswerViewModel::class.java)
    }

    fun setClickText() {
        Toast.makeText(this, "클릭가능", Toast.LENGTH_SHORT).show()
    }

    private fun observePublicSwitch() {
        answerViewModel.isPublic.observe(this) { isPublic ->
            val layoutParams =
                binding.linearAnswerPublic.layoutParams!! as ConstraintLayout.LayoutParams
            if (isPublic) {
                layoutParams.setMargins(0, 0, 0, 64.dp)
            } else {
                layoutParams.setMargins(0, 0, 0, 20.dp)
            }
            binding.linearAnswerPublic.layoutParams = layoutParams
        }
    }

    private fun setSwitchListener() {
        binding.switchAnswerPublic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                answerViewModel.setPublicTrue()
            } else {
                answerViewModel.setPublicFalse()
            }
        }
    }

    private fun initEditText(id: Int) {
        uiScope.launch {
            answerViewModel.initEditText(id)
        }
    }

    override fun onPause() {
        super.onPause()
        answerViewModel.storeAnswer()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewJob.cancel()
    }
}