package com.teambeme.beme.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teambeme.beme.explore.view.ExploreFragment
import com.teambeme.beme.following.view.FollowingFragment
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.mypage.view.MyPageFragment
import com.teambeme.beme.mypage.view.MyScrapFragment
import com.teambeme.beme.mypage.view.MyWriteFragment

class MyPageViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    var fragments=listOf<Fragment>()
    override fun getCount(): Int =fragments.size
    override fun getItem(position: Int): Fragment =fragments[position]

}