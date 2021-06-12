package com.teambeme.beme.presentation.setting.view

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityOpenSourceBinding
import com.teambeme.beme.presentation.setting.adapter.OpenSourceAdapter
import com.teambeme.beme.presentation.setting.model.OpenSourceData
import com.teambeme.beme.util.StatusBarUtil
import com.teambeme.beme.util.dp

class OpenSourceActivity :
    BindingActivity<ActivityOpenSourceBinding>(R.layout.activity_open_source) {
    private lateinit var openSourceAdapter: OpenSourceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        initView()
    }

    private fun initView() {
        setAdapter()
        setUIListener()
    }

    private fun setAdapter() {
        openSourceAdapter = OpenSourceAdapter()
        binding.rvOpensourceList.adapter = openSourceAdapter
        openSourceAdapter.submitList(getOpenSourceList())
        binding.rvOpensourceList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = 8.dp
                outRect.bottom = 8.dp
            }
        })
    }

    private fun setUIListener() {
        binding.imgOpensourceBack.setOnClickListener { finish() }
    }

    private fun getOpenSourceList(): List<OpenSourceData> = listOf(
        OpenSourceData(
            openSource = "Dots Indicator",
            link = "https://github.com/tommybuonomo/dotsindicator",
            copyright = "Copyright 2016 Tommy Buonomo."
        ),
        OpenSourceData(
            openSource = "Android Constraint Layout Library",
            link = "https://developer.android.com/reference/android/support/constraint/packages",
            copyright = "Copyright 2017 The Android Open Source Project."
        ),
        OpenSourceData(
            openSource = "Android Databinding Library",
            link = "https://developer.android.com/reference/android/databinding/packages",
            copyright = "Copyright 2018 The Android Open Source Project."
        ),
        OpenSourceData(
            openSource = "AndroidX Library",
            link = "https://developer.android.com/reference/androidx/packages",
            copyright = "Copyright 2018 The Android Open Source Project."
        ),
        OpenSourceData(
            openSource = "Android KTX",
            link = "https://github.com/android/android-ktx",
            copyright = "Copyright 2018 The Android Open Source Project."
        ),
        OpenSourceData(
            openSource = "Android Material Components",
            link = "https://developer.android.com/reference/com/google/android/material/packages",
            copyright = "Copyright 2018 The Android Open Source Project"
        ),
        OpenSourceData(
            openSource = "Annotations for JVM-based languages",
            link = "https://github.com/JetBrains/java-annotations",
            copyright = "Copyright 2000-2016 JetBrains s.r.o."
        ),
        OpenSourceData(
            openSource = "CircleImageView",
            link = "https://github.com/hdodenhof/CircleImageView",
            copyright = "Copyright 2014 - 2020 Henning Dodenhof"
        ),
        OpenSourceData(
            openSource = "Google Gson",
            link = "https://github.com/google/gson",
            copyright = "Copyright 2008 Google Inc."
        ),
        OpenSourceData(
            openSource = "Kotlin",
            link = "https://github.com/jetbrains/kotlin",
            copyright = "Copyright 2010-2020 JetBrains s.r.o."
        ),
        OpenSourceData(
            openSource = "Square OkHttp",
            link = "https://github.com/square/okhttp",
            copyright = "Copyright 2014 Square, Inc."
        ),
        OpenSourceData(
            openSource = "Square Retrofit",
            link = "https://github.com/square/retrofit",
            copyright = "Copyright 2013 Square, Inc."
        ),
        OpenSourceData(
            openSource = "Glide",
            link = "https://github.com/bumptech/glide",
            copyright = "Copyright 2014 Google, Inc."
        ),
        OpenSourceData(
            openSource = "TedPermission",
            link = "https://github.com/ParkSangGwon/TedPermission",
            copyright = "Copyright 2017 Ted Park."
        ),
        OpenSourceData(
            openSource = "Lottie Android",
            link = "https://github.com/airbnb/lottie-android/",
            copyright = "Copyright 2018 Airbnb, Inc."
        ),
        OpenSourceData(
            openSource = "Android-Image-Cropper",
            link = "https://github.com/ArthurHub/Android-Image-Cropper",
            copyright = "Copyright 2016, Arthur Teplitzki, 2013, Edmodo, Inc."
        ),
        OpenSourceData(
            openSource = "android-PullRefreshLayout",
            link = "https://github.com/baoyongzhang/android-PullRefreshLayout",
            copyright = "Copyright (c) 2014 baoyongzhang"
        )
    )
}
