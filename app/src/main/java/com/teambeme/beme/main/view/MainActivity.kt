package com.teambeme.beme.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityMainBinding
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.main.viewmodel.MainViewModel

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewPagerAdapter(this)
        setBottomNavigationSelectListener(binding.bnvMain)
    }

    private fun setBottomNavigationSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> binding.vpMain.currentItem = 0
                R.id.menu_main_explore -> binding.vpMain.currentItem = 1
                R.id.menu_main_following -> binding.vpMain.currentItem = 2
                R.id.menu_main_mypage -> binding.vpMain.currentItem = 3
            }
            true
        }
    }

    private fun setViewPagerAdapter(fragmentActivity: FragmentActivity) {
        val viewPagerAdapter = MainViewPagerAdapter(fragmentActivity)
        binding.vpMain.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
        }
    }

    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bnvMain.selectedItemId = when (position) {
                0 -> R.id.menu_main_home
                1 -> R.id.menu_main_explore
                2 -> R.id.menu_main_following
                3 -> R.id.menu_main_mypage
                else -> throw IllegalArgumentException("Wrong Position $position")
            }
        }
    }
}