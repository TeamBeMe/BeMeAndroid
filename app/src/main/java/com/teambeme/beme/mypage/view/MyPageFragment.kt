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
import com.teambeme.beme.data.remote.datasource.MyPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentMyPageBinding
import com.teambeme.beme.mypage.adapter.MyPageViewPagerAdapter
import com.teambeme.beme.mypage.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }
    private var isChangeProfile: Boolean = false
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
            isChangeProfile = true
        }
        binding.btnMypageProfile.setOnClickListener { editProfileClickListener() }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (isChangeProfile) {
            isChangeProfile = false
        } else {
            mypageViewModel.getMyProfile()
        }
    }

    private fun setViewPagerAdapter(fragmentManager: FragmentManager) {
        val pagerAdapter = MyPageViewPagerAdapter(this)
        pagerAdapter.addFragment(MyWriteFragment())
        pagerAdapter.addFragment(MyScrapFragment())
        val viewPager = binding.vpMypage
        viewPager.adapter = pagerAdapter
        val tabText = arrayListOf("내 글", "스크랩")
        TabLayoutMediator(binding.tabMypage, viewPager) { tab, position ->
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
        mypageViewModel.scrapFilterOnClickFalse()
    }
}