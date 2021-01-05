package com.teambeme.beme.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemThisWeekBackQuestionBinding
import com.teambeme.beme.generated.callback.OnClickListener
import com.teambeme.beme.notification.model.BackQuestionData

class BackQuestionAdapter(private val context: Context) :
    RecyclerView.Adapter<BackQuestionViewHolder>() {
    var datas = mutableListOf<BackQuestionData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackQuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemThisWeekBackQuestionBinding.inflate(inflater, parent, false)
        return BackQuestionViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: BackQuestionViewHolder, position: Int) {

        holder.bind(datas[position])
        holder.deleteBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (datas.isNotEmpty())
                    datas.remove(datas[position])
                notifyDataSetChanged()
            }
        })


    }
}

