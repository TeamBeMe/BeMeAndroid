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
import com.teambeme.beme.databinding.ItemMywriteBinding
import com.teambeme.beme.detail.view.DetailActivity
import com.teambeme.beme.mypage.model.ResponseMyAnswer
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyWriteAdapter(private val myViewModel: MyPageViewModel, private val context: Context) :
    ListAdapter<ResponseMyAnswer.Data.Answer, MyWriteAdapter.MyWriteViewHolder>(MyWriteDiffUtil()) {

    inner class MyWriteViewHolder(private val binding: ItemMywriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(write: ResponseMyAnswer.Data.Answer) {
            binding.myWrite = write
            setSecretBtnClickListener(binding, write)
            setClickListenerForGoDetail(binding, write, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMywriteBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_mywrite, parent, false)
        return MyWriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class MyWriteDiffUtil : DiffUtil.ItemCallback<ResponseMyAnswer.Data.Answer>() {
        override fun areItemsTheSame(
            oldItem: ResponseMyAnswer.Data.Answer,
            newItem: ResponseMyAnswer.Data.Answer
        ) =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(
            oldItem: ResponseMyAnswer.Data.Answer,
            newItem: ResponseMyAnswer.Data.Answer
        ) =
            (oldItem == newItem)
    }

    private fun setSecretBtnClickListener(
        binding: ItemMywriteBinding,
        myWriteData: ResponseMyAnswer.Data.Answer
    ) {
        binding.imgMywriteSecret.setOnClickListener {
            myViewModel.putPublic(myWriteData.id, myWriteData.publicFlag)
            if (myWriteData.publicFlag) {
                binding.imgMywriteSecret.setImageResource(R.drawable.ic_secret_on_mypage)
            } else {
                binding.imgMywriteSecret.setImageResource(R.drawable.ic_secret_off_mypage)
            }
        }
    }

    private fun setClickListenerForGoDetail(
        binding: ItemMywriteBinding,
        myWriteData: ResponseMyAnswer.Data.Answer,
        context: Context
    ) {
        binding.constraintMywrite.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("answerId", myWriteData.id)
            context.startActivity(intent)
        }
    }
}