package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private lateinit var scrapAdapter: MyScrapAdapter
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_scrap, container, false)
        binding.mpViewModel = mypageViewModel
        scrapAdapter = MyScrapAdapter()
        binding.rcvMyscrap.apply {
            adapter = scrapAdapter
            layoutManager = LinearLayoutManager(context)
        }
        mypageViewModel.setDummyScrap()

        mypageViewModel.mypageScrapData.observe(viewLifecycleOwner) { it ->
            it.let { scrapAdapter.replaceScrapList(it) }
        }
        mypageViewModel.isScrapFilterClicked.observe(viewLifecycleOwner) {
            filterClickListener(it)
        }
        mypageViewModel.scrapFilter.observe(viewLifecycleOwner) {
            getSheetDataListener(it)
        }
        return binding.root
    }

    private fun filterClickListener(isFilterClicked: Boolean) {
        if (isFilterClicked) {
            val bottomSheetFragment = BottomScrapFragment()
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
            mypageViewModel.scrapFilterOnClickFalse()
        }
    }

    private fun getSheetDataListener(category: String) {
        Toast.makeText(context, category, Toast.LENGTH_SHORT).show()
    }
}