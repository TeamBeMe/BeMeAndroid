package com.teambeme.beme.answer.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teambeme.beme.R
import com.teambeme.beme.answer.viewmodel.AnswerViewModel
import com.teambeme.beme.answer.viewmodel.AnswerViewModelFactory
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.local.database.AppDatabase
import com.teambeme.beme.databinding.ActivityAnswerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnswerActivity : BindingActivity<ActivityAnswerBinding>(R.layout.activity_answer) {
    private var viewJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewJob)
    private lateinit var answerViewModel: AnswerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id", 0)
        val answerDao = AppDatabase.getInstance(applicationContext).answerDao
        initViewModel(answerDao)
        initEditText(id)
    }

    private fun initViewModel(answerDao: AnswerDao) {
        answerViewModel = ViewModelProvider(
            this,
            AnswerViewModelFactory(answerDao)
        ).get(AnswerViewModel::class.java)
        binding.answerViewModel = answerViewModel
    }

    private fun initEditText(id: Int) {
        uiScope.launch {
            val text = answerViewModel.initEditText(id)
            binding.txtAnswerAnswer.setText(text)
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