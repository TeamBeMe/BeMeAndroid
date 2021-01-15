package com.teambeme.beme.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.FbTokenRegisterDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityMainBinding
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.main.repository.MainRepositoryImpl
import com.teambeme.beme.main.viewmodel.MainViewModel
import com.teambeme.beme.main.viewmodel.MainViewModelFactory
import com.teambeme.beme.util.StatusBarUtil

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModelFactory =
        MainViewModelFactory(MainRepositoryImpl(FbTokenRegisterDataSourceImpl(RetrofitObjects.getFbTokenRegisterService())))
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        mainViewModel.getFireBaseToken()
        setViewPagerAdapter(this)
        setBottomNavigationSelectListener(binding.bnvMain)
    }

    private fun setBottomNavigationSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    binding.vpMain.currentItem = 0
                    StatusBarUtil.setStatusBar(this, Color.BLACK)
                    setViewPagerDefaultPosition()
                }
                R.id.menu_main_explore -> {
                    binding.vpMain.currentItem = 1
                    StatusBarUtil.setStatusBar(
                        this,
                        resources.getColor(R.color.explore_background_gray, null)
                    )
                }
                R.id.menu_main_following -> {
                    binding.vpMain.currentItem = 2
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
                R.id.menu_main_mypage -> {
                    binding.vpMain.currentItem = 3
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
            }
            true
        }
    }

    private fun setViewPagerAdapter(fragmentActivity: FragmentActivity) {
        val viewPagerAdapter = MainViewPagerAdapter(fragmentActivity)
        binding.vpMain.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
            requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun setViewPagerDefaultPosition() {
        val homeFragment = supportFragmentManager.findFragmentByTag("f0") as HomeFragment
        homeFragment.returnToDefaultPosition()
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