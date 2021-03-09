package com.teambeme.beme.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.teambeme.beme.explore.view.ExploreFragment
import com.teambeme.beme.following.view.FollowingFragment
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.main.model.ScreenName
import com.teambeme.beme.mypage.view.MyPageFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = MAIN_PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        recordScreen(position)
        return when (position) {
            0 -> HomeFragment()
            1 -> ExploreFragment()
            2 -> FollowingFragment()
            else -> MyPageFragment()
        }
    }

    private fun recordScreen(position: Int) {
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, ScreenName.of(position))
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }

    companion object {
        const val MAIN_PAGE_NUM = 4
    }
}