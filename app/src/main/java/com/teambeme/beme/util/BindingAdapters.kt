package com.teambeme.beme.util

import android.graphics.Typeface
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
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

    @BindingAdapter("following:setTextForUnAnswered")
    @JvmStatic
    fun setTextForUnAnswered(textView: TextView, userNickname: String) {
        textView.text = "아직 " + userNickname + "님이 답하지 않은 질문입니다.\n" +
                "답변하시고 글을 보시겠습니까?"
    }

    @BindingAdapter("setSrcFromUrl")
    @JvmStatic
    fun setSrcFromUrl(imageView: ImageView, url: String?) {
        if (url == null) {
            imageView.setImageResource(R.drawable.ic_dark_profile)
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

    @BindingAdapter("answer:setDate")
    @JvmStatic
    fun setAnswerCreatedDate(textView: TextView, date: String) {
        Log.d("answer", "called setAnswerCreatedDate")
        textView.text = date.substring(0..9)
    }

    @BindingAdapter("home:category", "home:answerIdx", "home:categoryColor")
    @JvmStatic
    fun setCategoryText(textView: TextView, category: String, answerIdx: String?, color: Int) {
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
            textView.text = spannableString
        } else {
            val text = "[ " + category + "에 관한 질문 ]"
            textView.text = text
        }
    }

    @BindingAdapter("user:setNickName", "user:setQuestionTitle", "user:setType")
    @JvmStatic
    fun setReplyText(textView: TextView, id: String, title: String?, type: String) {
        when (type) {
            "comment" -> {
                val activity = id + "님이 " + title + "에 대한 나의 글에 댓글을 달았습니다."
                val spannableString = SpannableStringBuilder(activity)
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    id.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.append(spannableString)
            }
            "cocomment" -> {
                val activity = id + "님이 " + title + "에 대한 나의 댓글에 답글을 달았습니다."
                val spannableString = SpannableStringBuilder(activity)
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    id.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.append(spannableString)
            }
            "follow" -> {
                val activity = id + "님이 나를 팔로우했습니다."
                val spannableString = SpannableStringBuilder(activity)
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    id.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView.append(spannableString)
            }
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
            chip.isChecked = false
            val text = "팔로잉"
            chip.text = text
        } else {
            chip.isChecked = true
            val text = "팔로우"
            chip.text = text
        }
    }

    @BindingAdapter("home:publicButtonSrc")
    @JvmStatic
    fun setSrc(imgButton: ImageButton, publicFlag: Int) {
        when (publicFlag) {
            0 -> imgButton.setImageResource(R.drawable.ic_lock)
            else -> imgButton.setImageResource(R.drawable.ic_unlock)
        }
    }

    @BindingAdapter("detail:isVisible", "detail:comment")
    @JvmStatic
    fun setSecretReply(textView: TextView, isVisible: Boolean?, comment: String?) {
        if (isVisible == true) {
            textView.text = comment
        } else {
            val text = "비밀 댓글 입니다."
            textView.text = text
        }
    }

    @BindingAdapter("detail:isVisible", "detail:nickname")
    @JvmStatic
    fun setSecretId(textView: TextView, isVisible: Boolean?, nickname: String?) {
        if (isVisible == true) {
            textView.text = nickname
        } else {
            val text = "익명"
            textView.text = text
        }
    }

    @BindingAdapter("setSecretReply")
    @JvmStatic
    fun setSecretReply(textView: TextView, isVisible: Boolean?) {
        if (isVisible == true) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

    @BindingAdapter("mypage:setSrcFromUrl", "mypage:isVisible")
    @JvmStatic
    fun setSrcFromUrlProfile(imageView: ImageView, url: String?, visible: Boolean?) {
        if (visible == true) {
            imageView.visibility = View.VISIBLE
            if (url == null) {
                imageView.setImageResource(R.drawable.ic_dark_profile)
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

    @BindingAdapter("detail:setReplyUrl", "detail:isVisible")
    @JvmStatic
    fun setSrcReplyUrl(imageView: ImageView, url: String?, visible: Boolean?) {
        if (visible == true) {
            imageView.visibility = View.VISIBLE
            if (url == null) {
                imageView.setImageResource(R.drawable.ic_dark_profile)
            } else {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
            }
        } else {
            imageView.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("mypage:category", "mypage:isAuthor", "mypage:answerIdx")
    @JvmStatic
    fun setScrapCategoryText(
        textView: TextView,
        category: String?,
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
    fun setScrapNameText(textView: TextView, nickname: String?, isAuthor: Boolean?) {
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