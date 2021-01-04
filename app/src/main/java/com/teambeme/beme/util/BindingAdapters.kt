package com.teambeme.beme.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teambeme.beme.explore.adapter.OtherquestionsRcvAdapter
import com.teambeme.beme.explore.model.OtherquestionsData

object BindingAdapters {
    @BindingAdapter("setOtherquestions")
    @JvmStatic
    fun setOtherquestions(
        recyclerView: RecyclerView,
        otherquestions: MutableList<OtherquestionsData>?
    ) {
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

    @BindingAdapter("app:setSrcFromUrl")
    @JvmStatic
    fun setSrcFromUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    @InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
    @JvmStatic
    fun getEditTextString(view: EditText): String = view.text.toString()

    @BindingAdapter("android:text")
    @JvmStatic
    fun setEditTextString(view: EditText, text: String?) {
        if (text == null) {
            view.setText("")
        } else {
            if (view.text.toString() != text) view.setText(text)
        }
    }

    @BindingAdapter("textAttrChanged")
    @JvmStatic
    fun setEditTextTextWatcher(view: EditText, textAttrChanged: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textAttrChanged.onChange()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}