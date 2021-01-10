package com.teambeme.beme.mypage.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayoutMediator
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyPageBinding
import com.teambeme.beme.mypage.adapter.MyPageViewPagerAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        binding.lifecycleOwner = this
        setViewPagerAdapter(childFragmentManager)
        binding.myPageViewModel = mypageViewModel
        mypageViewModel.profileUri.observe(viewLifecycleOwner) {
            editProfileListener(it)
        }
        binding.btnMypageProfile.setOnClickListener { editProfileClickListener() }
        return binding.root
    }

    private fun setViewPagerAdapter(fragmentManager: FragmentManager) {
        val pagerAdapter = MyPageViewPagerAdapter(requireActivity())
        pagerAdapter.addFragment(MyWriteFragment())
        pagerAdapter.addFragment(MyScrapFragment())
        val viewPager = binding.vpMypage
        viewPager.adapter = pagerAdapter
        val tabText = arrayListOf("내 글", "스크랩")
        TabLayoutMediator(binding.tabMypage, viewPager) { tab, position ->
            tab.text = tabText[position]
        }.attach()
    }

    private fun editProfileListener(uri: Uri) {
        binding.imgMypageProfile.setImageURI(uri)
    }

    private fun editProfileClickListener() {
        val bottomSheetFragment = BottomProfileFragment()
        bottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetFragment.tag
        )
        mypageViewModel.scrapFilterOnClickFalse()
    }
}