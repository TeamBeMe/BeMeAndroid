package com.teambeme.beme.otherpage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemOtherPageBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.otherpage.model.ResponseOtherData.Data.Answer
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModel

class OtherPageAdapter(
    private val otherViewModel: OtherPageViewModel,
    private val context: Context
) :
    ListAdapter<Answer, OtherPageAdapter.OtherPageViewHolder>(OtherPageDiffUtil()) {

    inner class OtherPageViewHolder(private val binding: ItemOtherPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) {
            binding.answer = answer
            setOnClickListenerGoDetail(binding, answer, context)
            setOnClickListenerBtnScrap(binding, answer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherPageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemOtherPageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_other_page, parent, false)
        return OtherPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class OtherPageDiffUtil : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem.answerIdx == newItem.answerIdx)

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem == newItem)
    }

    private fun setOnClickListenerGoDetail(
        binding: ItemOtherPageBinding,
        answer: Answer,
        context: Context
    ) {
        binding.constraintOtheritem.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", answer.id)
            context.startActivity(intent)
        }
    }

    private fun setOnClickListenerBtnScrap(
        binding: ItemOtherPageBinding,
        answer: Answer
    ) {
        binding.imgOtheritemScrap.setOnClickListener {
            otherViewModel.putScrap(answer.id)
            answer.isScrapped = !answer.isScrapped
            if (answer.isScrapped) {
                binding.imgOtheritemScrap.setImageResource(R.drawable.ic_scrap_on_mypage)
            } else {
                binding.imgOtheritemScrap.setImageResource(R.drawable.ic_scrap_off_mypage)
            }
        }
    }
}