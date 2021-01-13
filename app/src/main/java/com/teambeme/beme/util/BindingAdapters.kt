package com.teambeme.beme.util

import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.teambeme.beme.R

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

    @BindingAdapter("following:setTextForUnAnswered")
    @JvmStatic
    fun setTextForUnAnswered(textView: TextView, userNickname: String) {
        textView.text = "아직 " + userNickname + "님이 답하지 않은 질문입니다.\n" +
                "답변을 하시고 글을 보시겠습니까?"
    }

    @BindingAdapter("setSrcFromUrl")
    @JvmStatic
    fun setSrcFromUrl(imageView: ImageView, url: String?) {
        if (url == null) {
            imageView.setImageResource(R.drawable.img_profile_sample)
        } else {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .into(imageView)
        }
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

    @BindingAdapter("mypage:category", "mypage:answerIdx")
    @JvmStatic
    fun setPageCategoryText(textView: TextView, category: String, answerIdx: Int?) {
        if (answerIdx != null) {
            val text = "[ " + category + "에 관한 " + answerIdx + "번째 질문 ]"
            textView.text = text
        } else {
            val text = "[ " + category + "에 관한 질문 ]"
            textView.text = text
        }
    }

    @BindingAdapter("attendCount")
    @JvmStatic
    fun setAttendCount(textView: TextView, attend: Int) {
        val text = "연속 출석 $attend"
        textView.text = text
    }

    @BindingAdapter("allAnswerCount")
    @JvmStatic
    fun setAllAnswerCount(textView: TextView, count: Int) {
        val text = "답한 질문 $count"
        textView.text = text
    }

    @BindingAdapter("setFollow")
    @JvmStatic
    fun setFollow(chip: Chip, isFollow: Boolean) {
        if (isFollow) {
            chip.isChecked = true
            val text = "팔로잉"
            chip.text = text
        } else {
            chip.isChecked = false
            val text = "팔로우"
            chip.text = text
        }
    }

    @BindingAdapter("detail:isVisible", "detail:comment")
    @JvmStatic
    fun setSecretReply(textView: TextView, isVisible: Boolean, comment: String) {
        if (isVisible) {
            textView.text = comment
        } else {
            val text = "비밀 댓글 입니다."
            textView.text = text
        }
    }

    @BindingAdapter("detail:isVisible", "detail:nickname")
    @JvmStatic
    fun setSecretId(textView: TextView, isVisible: Boolean, nickname: String) {
        if (isVisible) {
            textView.text = nickname
        } else {
            val text = "익명"
            textView.text = text
        }
    }

    @BindingAdapter("mypage:setSrcFromUrl", "mypage:isVisible")
    @JvmStatic
    fun setSrcFromUrlProfile(imageView: ImageView, url: String?, visible: Boolean) {
        if (visible) {
            imageView.visibility = View.VISIBLE
            if (url == null) {
                imageView.setImageResource(R.drawable.img_profile_sample)
            } else {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
            }
        } else {
            imageView.visibility = View.GONE
        }
    }

    @BindingAdapter("mypage:category", "mypage:isAuthor", "mypage:answerIdx")
    @JvmStatic
    fun setScrapCategoryText(
        textView: TextView,
        category: String,
        isAuthor: Boolean?,
        answerIdx: Int?
    ) {
        if (isAuthor == true) {
            val text = "[ " + category + "에 관한 " + answerIdx + "번째 질문 ]"
            textView.text = text
        } else {
            val text = "[ " + category + "에 관한 질문 ]"
            textView.text = text
        }
    }

    @BindingAdapter("mypage:nickname", "mypage:isAuthor")
    @JvmStatic
    fun setScrapNameText(textView: TextView, nickname: String, isAuthor: Boolean?) {
        if (isAuthor == true) {
            textView.visibility = View.GONE
        } else {
            val text = nickname
            textView.text = text
            textView.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("setSecretImage")
    @JvmStatic
    fun setSecretImage(imageView: ImageView, isAuthor: Boolean?) {
        if (isAuthor == false) {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
        }
    }
}