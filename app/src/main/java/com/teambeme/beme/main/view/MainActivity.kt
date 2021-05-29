package com.teambeme.beme.main.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.local.singleton.BeMeRepository
import com.teambeme.beme.databinding.ActivityMainBinding
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.main.viewmodel.EventViewModel
import com.teambeme.beme.main.viewmodel.MainViewModel
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "BeMeApplication.TAG",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            } else {
                val token = task.result
                BeMeRepository.fireBaseToken = token ?: "SomeThing"
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("BeMeApplication.TAG", msg)
            }
        })
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        mainViewModel.getFireBaseToken()
        setViewPagerAdapter(this)
        setBottomNavigationSelectListener(binding.bnvMain)
        setBottomNavigationReSelectListener(binding.bnvMain)
        if (intent.getBooleanExtra("isOpenFromPushAlarm", false)) {
            binding.vpMain.setCurrentItem(2, true)
        }
    }

    private fun setBottomNavigationSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_explore -> {
                    binding.vpMain.currentItem = 0
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
                R.id.menu_main_following -> {
                    binding.vpMain.currentItem = 1
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
                R.id.menu_main_home -> {
                    binding.vpMain.currentItem = 2
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.black, null))
                }
                R.id.menu_main_mypage -> {
                    binding.vpMain.currentItem = 3
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
            }
            eventViewModel.buttonClickedAt(binding.vpMain.currentItem)
            true
        }
    }

    private fun setBottomNavigationReSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    eventViewModel.buttonClickedAt(0)
                }
                R.id.menu_main_explore -> {
                    eventViewModel.buttonClickedAt(1)
                }
                R.id.menu_main_following -> {
                    eventViewModel.buttonClickedAt(2)
                }
                R.id.menu_main_mypage -> {
                    eventViewModel.buttonClickedAt(3)
                }
            }
        }
    }

    private fun setViewPagerAdapter(fragmentActivity: FragmentActivity) {
        val viewPagerAdapter = MainViewPagerAdapter(fragmentActivity)
        binding.vpMain.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
            requestDisallowInterceptTouchEvent(false)
            isUserInputEnabled = false
        }
    }

    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bnvMain.selectedItemId = when (position) {
                0 -> R.id.menu_main_explore
                1 -> R.id.menu_main_following
                2 -> R.id.menu_main_home
                3 -> R.id.menu_main_mypage
                else -> throw IllegalArgumentException("Wrong Position $position")
            }
        }
    }
}