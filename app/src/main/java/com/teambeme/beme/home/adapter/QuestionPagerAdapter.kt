package com.teambeme.beme.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.databinding.ItemHomeMoreQuestionBinding
import com.teambeme.beme.databinding.ItemHomeQuestionBinding
import com.teambeme.beme.home.model.ResponseQuestionData
import com.teambeme.beme.home.view.AnswerSuggestFragment
import com.teambeme.beme.home.view.TransitionPublicFragment

class QuestionPagerAdapter(private val supportFragmentManager: FragmentManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var questionList = mutableListOf<ResponseQuestionData.Answer>()

    class QuestionViewHolder(
        private val context: Context,
        private val binding: ItemHomeQuestionBinding,
        private val fragmentManager: FragmentManager
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(answer: ResponseQuestionData.Answer) {
            binding.answer = answer
            binding.btnHomeAnswer.setOnClickListener {
                val intent = Intent(context, AnswerActivity::class.java)
                intent.apply {
                    putExtra("id", answer.id)
                    putExtra("content", answer.content)
                    putExtra("isPublic", answer.publicFlag)
                }
                context.startActivity(intent)
            }
            binding.imgQuestionLock.setOnClickListener {
                TransitionPublicFragment().show(fragmentManager, "TransitionPublic")
            }
        }
    }

    class MoreQuestionViewHolder(
        private val binding: ItemHomeMoreQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(supportFragmentManager: FragmentManager) {
            binding.btnHomeMoreQuestion.setOnClickListener {
                val answerSuggestFragment = AnswerSuggestFragment()
                answerSuggestFragment.show(supportFragmentManager, "CustomDialog")
            }
        }
    }

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
                QuestionViewHolder(parent.context, binding, supportFragmentManager)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != questionList.size) {
            with(holder as QuestionViewHolder) { holder.onBind(questionList[position]) }
        } else {
            with(holder as MoreQuestionViewHolder) {
                holder.onBind(supportFragmentManager)
            }
        }
    }

    override fun getItemCount() = questionList.size + 1

    fun replaceQuestionList(list: List<ResponseQuestionData.Answer>) {
        questionList = list.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}