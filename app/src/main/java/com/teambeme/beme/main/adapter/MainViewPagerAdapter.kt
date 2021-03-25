package com.teambeme.beme.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teambeme.beme.explore.view.ExploreFragment
import com.teambeme.beme.following.view.FollowingFragment
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.mypage.view.MyPageFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = MAIN_PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExploreFragment()
            1 -> FollowingFragment()
            2 -> HomeFragment()
            else -> MyPageFragment()
        }
    }

    companion object {
        const val MAIN_PAGE_NUM = 4
    }
}