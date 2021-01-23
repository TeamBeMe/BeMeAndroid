package com.teambeme.beme.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teambeme.beme.mypage.view.MyScrapFragment
import com.teambeme.beme.mypage.view.MyWriteFragment

class MyPageViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyWriteFragment()
            else -> MyScrapFragment()
        }
    }
}
