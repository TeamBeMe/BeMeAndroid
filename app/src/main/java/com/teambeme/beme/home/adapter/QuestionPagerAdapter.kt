package com.teambeme.beme.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.databinding.ItemHomeMoreQuestionBinding
import com.teambeme.beme.databinding.ItemHomeQuestionBinding
import com.teambeme.beme.home.model.Question

class QuestionPagerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var questionList = mutableListOf<Question>()

    class QuestionViewHolder(
        private val context: Context,
        private val binding: ItemHomeQuestionBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(question: Question) {
            binding.question = question
            binding.textChangeQuestion = "질문 변경하기"
            binding.textEdit = "질문 변경하기"
            binding.btnHomeAnswer.setOnClickListener {
                val intent = Intent(context, AnswerActivity::class.java)
                intent.putExtra("id", question.id)
                context.startActivity(intent)
            }
        }
    }

    class MoreQuestionViewHolder(private val binding: ItemHomeMoreQuestionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            questionList.size -> TYPE_HEADER
            else -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> {
                val binding: ItemHomeMoreQuestionBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.item_home_more_question,
                        parent,
                        false
                    )
                MoreQuestionViewHolder(binding)
            }
            else -> {
                val binding: ItemHomeQuestionBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.item_home_question,
                        parent,
                        false
                    )
                QuestionViewHolder(parent.context, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != questionList.size) {
            with(holder as QuestionViewHolder) { holder.onBind(questionList[position]) }
        }
    }

    override fun getItemCount() = questionList.size + 1

    fun replaceQuestionList(list: List<Question>) {
        questionList = list.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}