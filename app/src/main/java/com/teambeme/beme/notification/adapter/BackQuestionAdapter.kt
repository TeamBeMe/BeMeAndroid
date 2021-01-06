package com.teambeme.beme.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.notification.model.BackQuestionData

class BackQuestionAdapter :
    RecyclerView.Adapter<BackQuestionAdapter.BackQuestionViewHolder>() {
    private var datas = mutableListOf<BackQuestionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackQuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemThisWeekBackQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_this_week_back_question, parent, false)
        return BackQuestionViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: BackQuestionViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    fun replaceQuestionList(list: MutableList<BackQuestionData>) {
        datas = list
        notifyDataSetChanged()
    }

    inner class BackQuestionViewHolder(private val binding: ItemThisWeekBackQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(backQuestionData: BackQuestionData) {
            with(binding) {
                backQuestion = backQuestionData
                executePendingBindings()
            }
        }

    }
}
