package com.teambeme.beme.mypage.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyPageBinding
import com.teambeme.beme.main.viewmodel.EventViewModel
import com.teambeme.beme.mypage.adapter.MyPageViewPagerAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.setting.view.SettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    private val eventViewModel: EventViewModel by activityViewModels()
    private var isChangeProfile: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setViewPagerAdapter()
        binding.myPageViewModel = myPageViewModel
        myPageViewModel.profileUri.observe(viewLifecycleOwner) {
            editProfileListener(it)
            isChangeProfile = true
        }
        binding.btnMypageProfile.setOnClickListener { editProfileClickListener() }
        settingClickListener()
        setScrollToTop()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (isChangeProfile) {
            isChangeProfile = false
        } else {
            myPageViewModel.getMyProfile()
        }
    }

    private fun settingClickListener() {
        binding.imgMypageSetting.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setViewPagerAdapter() {
        val pagerAdapter = MyPageViewPagerAdapter(this)
        binding.vpMypage.adapter = pagerAdapter
        val tabText = arrayListOf("내 글", "스크랩")
        TabLayoutMediator(binding.tabMypage, binding.vpMypage) { tab, position ->
            tab.text = tabText[position]
        }.attach()
    }

    private fun editProfileListener(uri: Uri?) {
        if (uri == null) {
            binding.imgMypageProfile.setImageResource(R.drawable.ic_dark_profile)
        } else {
            binding.imgMypageProfile.setImageURI(uri)
        }
    }

    private fun editProfileClickListener() {
        val bottomSheetFragment = BottomProfileFragment()
        bottomSheetFragment.show(
            childFragmentManager,
            bottomSheetFragment.tag
        )
        myPageViewModel.scrapFilterOnClickFalse()
    }

    private fun setScrollToTop() {
        binding.appbarLayoutMypage.setExpanded(true, true)
        eventViewModel.fourthButtonClicked.observe(viewLifecycleOwner) {
            myPageViewModel.scrollUp(binding.tabMypage.selectedTabPosition)
        }
    }
}