package com.teambeme.beme.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemOnboardingBinding
import com.teambeme.beme.onboarding.model.OnBoardingData

class OnBoardingAdapter() : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private var onBoardingDataList = listOf<OnBoardingData>()

    class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoardingData: OnBoardingData) {
            binding.data = onBoardingData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemOnboardingBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_onboarding, parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingDataList[position])
    }

    override fun getItemCount() = onBoardingDataList.size

    fun replaceList(list: List<OnBoardingData>) {
        onBoardingDataList = list
        notifyDataSetChanged()
    }
}