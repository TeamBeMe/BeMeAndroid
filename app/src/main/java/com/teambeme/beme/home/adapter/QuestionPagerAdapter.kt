package com.teambeme.beme.home.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.databinding.ItemHomeMoreQuestionBinding
import com.teambeme.beme.databinding.ItemHomeQuestionBinding
import com.teambeme.beme.home.model.Answer
import com.teambeme.beme.home.view.InfoChangeFragment
import com.teambeme.beme.home.view.InfoChangeFragment.InfoChangeClickListener
import com.teambeme.beme.home.view.TransitionPublicFragment
import com.teambeme.beme.home.viewmodel.HomeViewModel

class QuestionPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var answerList = mutableListOf<Answer>()

    inner class QuestionViewHolder(
        private val context: Context,
        private val binding: ItemHomeQuestionBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(answer: Answer, position: Int) {
            binding.answer = answer
            binding.btnHomeAnswer.setOnClickListener {
                val intent = Intent(context, AnswerActivity::class.java)
                intent.putExtra("id", answer.id)
                context.startActivity(intent)
            }
            binding.imgQuestionLock.setOnClickListener {
                TransitionPublicFragment(answerList[position].publicFlag,
                    object : TransitionPublicFragment.ChangePublicClickListener {
                        override fun onClick() {
                            homeViewModel.changePublic(position)
                        }
                    }
                ).show(fragmentManager, "TransitionPublic")
            }
            binding.txtHomeEdit.setOnClickListener {
                InfoChangeFragment(object : InfoChangeClickListener {
                    override fun changeQuestion() {
                        homeViewModel.changeQuestion(position)
                    }

                    override fun deleteAnswer() {
                        homeViewModel.deleteAnswer(position)
                    }
                }).show(
                    fragmentManager,
                    "InfoChangeBottomSheet"
                )
            }
        }
    }

    inner class MoreQuestionViewHolder(
        private val binding: ItemHomeMoreQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(supportFragmentManager: FragmentManager) {
            binding.btnHomeMoreQuestion.setOnClickListener {
//                if(answerList.all { answer -> answer.answerDate != null }) {
                homeViewModel.getMoreQuestion()
//                } else {
//                    val answerSuggestFragment = AnswerSuggestFragment()
//                    answerSuggestFragment.show(supportFragmentManager, "CustomDialog")
//                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            answerList.size -> TYPE_HEADER
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
        if (position != answerList.size) {
            with(holder as QuestionViewHolder) { holder.onBind(answerList[position], position) }
        } else {
            with(holder as MoreQuestionViewHolder) {
                holder.onBind(fragmentManager)
            }
        }
    }

    override fun getItemCount(): Int {
        return answerList.size + 1
    }

    fun replaceQuestionList(list: List<Answer>) {
        answerList = list.toMutableList()
        Log.d("Home QPA", answerList.toString())
        notifyDataSetChanged()
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}