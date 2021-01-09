package com.teambeme.beme.util

import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide

object BindingAdapters {
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

    @BindingAdapter("setSrcFromUrl")
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
    fun setEditTextString(view: EditText, text: MutableLiveData<String>) {
        if (text == null) {
            view.setText("")
        } else {
            if (view.text.toString() != text.value) view.setText(text.value)
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

    @BindingAdapter("home:underlineText")
    @JvmStatic
    fun setUnderLineText(view: TextView, text: String) {
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannableString
    }

    @BindingAdapter("home:setDate")
    @JvmStatic
    fun setHomeCreatedDate(textView: TextView, date: String) {
        textView.text = date.substring(0..9)
    }

    @BindingAdapter("home:category", "home:answerIdx", "home:categoryColor")
    @JvmStatic
    fun setCategoryText(textView: TextView, category: String, answerIdx: Int?, color: Int) {
        if (answerIdx != null) {
            val text = "[ " + category + "에 관한 " + answerIdx + "번째 질문 ]"
            val digit = answerIdx.toString().length
            val spannableString = SpannableStringBuilder(text)
            spannableString.setSpan(
                ForegroundColorSpan(color),
                7 + category.length,
                9 + category.length + digit,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                UnderlineSpan(),
                7 + category.length,
                9 + category.length + digit,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.append(spannableString)
        } else {
            val text = "[ " + category + "에 관한 질문 ]"
            textView.text = text
        }
    }
}