package com.teambeme.beme.mypage.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyPageBinding
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.mypage.adapter.MyPageViewPagerAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel


class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val mypageViewModel:MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_my_page, container, false)
        setViewPagerAdapter(childFragmentManager)
        return binding.root

    }
    private fun setViewPagerAdapter(fragmentManager: FragmentManager) {
        val viewpagerAdapter:MyPageViewPagerAdapter=MyPageViewPagerAdapter(fragmentManager)
        val viewPager=binding.vpMypage
        val taplayout=binding.tabMypage
        viewpagerAdapter.fragments=listOf(MyWriteFragment(),MyScrapFragment())
        binding.vpMypage.adapter=viewpagerAdapter

        binding.tabMypage.setupWithViewPager(viewPager)
        binding.tabMypage.apply{
            getTabAt(0)?.text="내 글"
            getTabAt(1)?.text="스크랩"
        }

    }


}