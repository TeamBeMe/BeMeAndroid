package com.teambeme.beme.explore

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.explore.adapter.OthermindsRcvAdapter
import com.teambeme.beme.explore.model.OthermindsData

object ExploreBinding {
    @BindingAdapter("setOtherminds")
    @JvmStatic
    fun setOtherminds(recyclerView: RecyclerView, otherminds: List<OthermindsData>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as OthermindsRcvAdapter) {
            otherminds?.let {
                submitList(otherminds)
            }
        }
    }
}