package com.teambeme.beme.mypage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemMyscrapBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.mypage.model.ResponseMyScrap
import com.teambeme.beme.otherpage.view.OtherPageActivity

class MyScrapAdapter(private val context: Context) :
    ListAdapter<ResponseMyScrap.Data.Answer, MyScrapAdapter.MyScrapViewHolder>(MyScrapDiffUtil()) {
    inner class MyScrapViewHolder(private val binding: ItemMyscrapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(scrap: ResponseMyScrap.Data.Answer) {
            binding.myScrap = scrap
            setClickListenerForGoDetail(binding, scrap, context)
            setClickListenerForGoProfilePage(binding, scrap, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyScrapViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyscrapBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_myscrap, parent, false)
        return MyScrapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyScrapViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class MyScrapDiffUtil : DiffUtil.ItemCallback<ResponseMyScrap.Data.Answer>() {
        override fun areItemsTheSame(
            oldItem: ResponseMyScrap.Data.Answer,
            newItem: ResponseMyScrap.Data.Answer
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseMyScrap.Data.Answer,
            newItem: ResponseMyScrap.Data.Answer
        ) =
            (oldItem == newItem)
    }

    private fun setClickListenerForGoDetail(
        binding: ItemMyscrapBinding,
        myScrapData: ResponseMyScrap.Data.Answer,
        context: Context
    ) {
        binding.constraintMyscrap.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", myScrapData.id)
            context.startActivity(intent)
        }
    }

    private fun setClickListenerForGoProfilePage(
        binding: ItemMyscrapBinding,
        myScrapData: ResponseMyScrap.Data.Answer,
        context: Context
    ) {
        binding.imgMyscrapProfile.setOnClickListener {
            val intent = Intent(context, OtherPageActivity::class.java)
            intent.putExtra("userId", myScrapData.userId)
            context.startActivity(intent)
        }
    }
}