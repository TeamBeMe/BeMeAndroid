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
import com.teambeme.beme.data.remote.datasource.FbTokenRegisterDataSourceImpl
import com.teambeme.beme.data.local.singleton.BeMeAuthPreference
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityMainBinding
import com.teambeme.beme.explore.view.ExploreFragment
import com.teambeme.beme.following.view.FollowingFragment
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.main.repository.MainRepositoryImpl
import com.teambeme.beme.main.viewmodel.MainViewModel
import com.teambeme.beme.main.viewmodel.MainViewModelFactory
import com.teambeme.beme.mypage.view.MyPageFragment
import com.teambeme.beme.util.StatusBarUtil

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModelFactory =
        MainViewModelFactory(MainRepositoryImpl(FbTokenRegisterDataSourceImpl(RetrofitObjects.getFbTokenRegisterService())))
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }
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
                // Get new FCM registration token
                val token = task.result
                BeMeAuthPreference.fireBaseToken = token ?: "SomeThing"
                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("BeMeApplication.TAG", msg)
            }
        })
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
        mainViewModel.getFireBaseToken()
        setViewPagerAdapter(this)
        setBottomNavigationSelectListener(binding.bnvMain)
        setBottomNavigationReSelectListener(binding.bnvMain)
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
                    setViewPagerDefaultPosition()
                }
                R.id.menu_main_mypage -> {
                    binding.vpMain.currentItem = 3
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
            }
            true
        }
    }

    private fun setBottomNavigationReSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_explore -> {
                    setExploreFragmentScrollToTop()
                }
                R.id.menu_main_following -> {
                    setFollowingFragmentScrollToTop()
                }
                R.id.menu_main_mypage -> {
                    setMyPageFragmentScrollToTop()
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

    private fun setViewPagerDefaultPosition() {
        val homeFragment = supportFragmentManager.findFragmentByTag("f2") as HomeFragment
        homeFragment.returnToDefaultPosition()
    }

    private fun setExploreFragmentScrollToTop() {
        val exploreFragment = supportFragmentManager.findFragmentByTag("f0") as ExploreFragment
        exploreFragment.setScrollToTop()
    }

    private fun setFollowingFragmentScrollToTop() {
        val followingFragment = supportFragmentManager.findFragmentByTag("f1") as FollowingFragment
        followingFragment.setScrollToTop()
    }

    private fun setMyPageFragmentScrollToTop() {
        val mypageFragment = supportFragmentManager.findFragmentByTag("f3") as MyPageFragment
        mypageFragment.setScrollToTop()
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