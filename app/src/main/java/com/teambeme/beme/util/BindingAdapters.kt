package com.teambeme.beme.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.explore.adapter.OthermindsRcvAdapter
import com.teambeme.beme.explore.adapter.OtherquestionsRcvAdapter
import com.teambeme.beme.explore.model.OthermindsData
import com.teambeme.beme.explore.model.OtherquestionsData

object BindingAdapters {
    @BindingAdapter("setOtherminds")
    @JvmStatic
    fun setOtherminds(recyclerView: RecyclerView, otherminds: List<OthermindsData>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as OthermindsRcvAdapter) {
            otherminds?.let {
                submitList(otherminds)
            }
        }
    }

    @BindingAdapter("setOtherquestions")
    @JvmStatic
    fun setOtherquestions(recyclerView: RecyclerView, otherquestions: MutableList<OtherquestionsData>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as OtherquestionsRcvAdapter) {
            otherquestions?.let {
                submitList(otherquestions)
            }
        }
    }

    @BindingAdapter("setCategory")
    @JvmStatic
    fun setCategory(textView: TextView, category: String) {
        textView.text = "[ " + category + "에 관한 질문 ]"
    }

    @BindingAdapter("setTime")
    @JvmStatic
    fun setTime(textView: TextView, time: String) {
        textView.text = time + "분 전"
    }
}