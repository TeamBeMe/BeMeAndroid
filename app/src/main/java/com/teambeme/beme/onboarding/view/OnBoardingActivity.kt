package com.teambeme.beme.onboarding.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.singleton.BeMeRepository
import com.teambeme.beme.databinding.ActivityOnBoardingBinding
import com.teambeme.beme.login.view.LoginActivity
import com.teambeme.beme.main.view.MainActivity
import com.teambeme.beme.onboarding.adapter.OnBoardingAdapter
import com.teambeme.beme.onboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity :
    BindingActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    private val viewModel by viewModels<OnBoardingViewModel>()
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        setViewPagerSetting()
        subscribeData()
        setUIListener()
    }

    private fun setViewPagerSetting() {
        onBoardingAdapter = OnBoardingAdapter()
        with(binding.vpOnboardingExplain) {
            adapter = onBoardingAdapter
            registerOnPageChangeCallback(providePageChangeCallback())
        }
        onBoardingAdapter.replaceList(viewModel.getOnBoardingDatas())
        binding.dotsOnboardingIndicator.setViewPager2(binding.vpOnboardingExplain)
    }

    private fun subscribeData() {
        with(viewModel) {
            responseValue.observe(this@OnBoardingActivity) {
                BeMeRepository.userToken = it?.data?.token ?: "Error"
                val intent =
                    Intent(this@OnBoardingActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            /* errorMessage.observe(this@OnBoardingActivity) { it.toast() } */
        }
    }

    private fun setUIListener() {
        with(binding) {
            txtOnboardingSkip.setOnClickListener { setNextActivity() }
            btnOnboardingStart.setOnClickListener { setNextActivity() }
        }
    }

    private fun setNextActivity() {
        BeMeRepository.isFirst = false
        when (BeMeRepository.userId.isNotEmpty()) {
            true -> viewModel.requestLogin()
            false -> navigateTo(LoginActivity::class.java)
        }
    }

    private fun providePageChangeCallback(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                    param(FirebaseAnalytics.Param.SCREEN_CLASS, "OnBoardingActivity")
                    param(FirebaseAnalytics.Param.SCREEN_NAME, getScreenName(position))
                }
                when (position) {
                    3 -> {
                        with(binding) {
                            txtOnboardingSkip.visibility = View.GONE
                            btnOnboardingStart.visibility = View.VISIBLE
                        }
                    }
                    else -> {
                        with(binding) {
                            txtOnboardingSkip.visibility = View.VISIBLE
                            btnOnboardingStart.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun getScreenName(position: Int): String {
        return when (position) {
            0 -> "ONBOARDING_1"
            1 -> "ONBOARDING_2"
            2 -> "ONBOARDING_3"
            else -> "ONBOARDING_4"
        }
    }

    private fun <T> navigateTo(activity: Class<T>) {
        val intent =
            Intent(this@OnBoardingActivity, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun String.toast() {
        Toast.makeText(this@OnBoardingActivity, this, Toast.LENGTH_SHORT).show()
    }
}
