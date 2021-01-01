package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyScrapBinding
import com.teambeme.beme.mypage.adapter.MyScrapAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyScrapFragment : Fragment() {
    private lateinit var binding: FragmentMyScrapBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_scrap, container, false)
        val scrapAdapter = MyScrapAdapter()
        binding.rcvMyscrap.apply {
            adapter = scrapAdapter
            layoutManager = LinearLayoutManager(context)
        }
        mypageViewModel.setDummyScrap()

        mypageViewModel.mypageScrapData.observe(viewLifecycleOwner) { it ->
            it.let { scrapAdapter.replaceScrapList(it) }
        }
        return binding.root
    }
}