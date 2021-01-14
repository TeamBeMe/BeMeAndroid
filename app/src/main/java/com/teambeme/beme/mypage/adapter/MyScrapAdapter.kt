package com.teambeme.beme.mypage.adapter

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

class MyScrapAdapter :
    ListAdapter<ResponseMyScrap.Data.Answer, MyScrapAdapter.MyScrapViewHolder>(MyScrapDiffUtil()) {
    private var scrapList = mutableListOf<ResponseMyScrap.Data.Answer>()

    class MyScrapViewHolder(private val binding: ItemMyscrapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(scrap: ResponseMyScrap.Data.Answer) {
            binding.myScrap = scrap
        }
        val public = binding.imgMyscrapPublic
        val profile = binding.imgMyscrapProfile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyScrapViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyscrapBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_myscrap, parent, false)
        return MyScrapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyScrapViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.putExtra("answerId", getItem(position).id)
            view.context.startActivity(intent)
        }
        holder.bind(getItem(position)).let {
            with(holder) {
                profile.setOnClickListener { view ->
                    val intent = Intent(view.context, OtherPageActivity::class.java)
                    intent.putExtra("userId", getItem(position).userId)
                    view.context.startActivity(intent)
                }
            }
        }
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

    fun replaceScrapList(list: List<ResponseMyScrap.Data.Answer>) {
        scrapList = list.toMutableList()
        submitList(scrapList)
    }
}