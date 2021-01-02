package com.teambeme.beme.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.notification.model.BackQuestionData
import com.teambeme.beme.notification.viewholder.BackQuestionViewHolder

class BackQuestionAdapter (private val context: Context) :
    RecyclerView.Adapter<BackQuestionViewHolder>(){

    var backQuestions = mutableListOf<BackQuestionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackQuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemThisWeekBackQuestionBinding.inflate(inflater, parent, false)
        return BackQuestionViewHolder(binding)
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: BackQuestionViewHolder, position: Int) {
        holder.bind(backQuestions[position])
    }
}
