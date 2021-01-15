package com.teambeme.beme.otherpage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemOtherPageBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.otherpage.model.ResponseOtherData.Data.Answer
import com.teambeme.beme.otherpage.viewmodel.OtherPageViewModel

class OtherPageAdapter(private val otherViewModel: OtherPageViewModel) :
    ListAdapter<Answer, OtherPageAdapter.OtherPageViewHolder>(OtherPageDiffUtil()) {

    class OtherPageViewHolder(private val binding: ItemOtherPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val scrap: ImageButton = binding.imgOtheritemScrap
        fun bind(answer: Answer) {
            binding.answer = answer
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
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.putExtra("answerId", getItem(position).id)
            view.context.startActivity(intent)
        }
        holder.bind(getItem(position)).let {
            with(holder) {
                scrap.setOnClickListener {
                    otherViewModel.setPosition(position)
                    if (getItem(position).isScrapped) {
                        getItem(position).isScrapped = false
                        scrap.setImageResource(R.drawable.ic_scrap_off_mypage)
                    } else {
                        getItem(position).isScrapped = true
                        scrap.setImageResource(R.drawable.ic_scrap_on_mypage)
                    }
                }
            }
        }
    }

    private class OtherPageDiffUtil : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem.answerIdx == newItem.answerIdx)

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer) =
            (oldItem == newItem)
    }
}