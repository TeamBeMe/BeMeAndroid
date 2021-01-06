package com.teambeme.beme.explore.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ActivityExploreDetailBinding
import com.teambeme.beme.databinding.ListExploredetailOtheranswersBinding
import com.teambeme.beme.explore.adapter.OtherquestionsRcvAdapter
import com.teambeme.beme.explore.viewmodel.ExploreViewModel
import com.teambeme.beme.util.StatusBarUtil

class ExploreDetailActivity : AppCompatActivity() {
    private val exploreDetailViewModel: ExploreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        val binding: ActivityExploreDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_explore_detail)
        binding.exploreDetailViewModel = exploreDetailViewModel
        binding.lifecycleOwner = this
        binding.othermindsTitle = intent.getStringExtra("othermindsTitle")
        setAdapter(binding)
        setObserve(binding)
        exploreDetailViewModel.setDummyOtheranswers()
        setClickListenerForPlusData(binding)
        setClickListenerForGoback(binding)
    }

    private fun setObserve(binding: ActivityExploreDetailBinding) {
        exploreDetailViewModel.otheranswersList.observe(this) { otheranswersList ->
            otheranswersList?.let {
                if (binding.rcvExploredetailOtheranswers.adapter != null) with(binding.rcvExploredetailOtheranswers.adapter as OtherquestionsRcvAdapter<*>) {
                    submitList(otheranswersList)
                }
            }
        }
    }

    private fun setAdapter(binding: ActivityExploreDetailBinding) {
        val otheranswersAdapter =
            OtherquestionsRcvAdapter<ListExploredetailOtheranswersBinding>(R.layout.list_exploredetail_otheranswers)
        binding.rcvExploredetailOtheranswers.adapter = otheranswersAdapter
    }

    private fun setClickListenerForPlusData(binding: ActivityExploreDetailBinding) {
        binding.btnExploredetailShowmore.setOnClickListener {
            exploreDetailViewModel.plusDummyOtheranswers()
        }
    }

    private fun setClickListenerForGoback(binding: ActivityExploreDetailBinding) {
        binding.btnExploredetailGoback.setOnClickListener {
            onBackPressed()
        }
    }
}