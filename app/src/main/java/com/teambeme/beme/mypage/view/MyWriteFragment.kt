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
import com.teambeme.beme.databinding.FragmentMyWriteBinding
import com.teambeme.beme.mypage.adapter.MyWriteAdapter
import com.teambeme.beme.mypage.model.MyWriteFilter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyWriteFragment : Fragment() {
    private lateinit var binding: FragmentMyWriteBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_write, container, false)
        binding.mpViewModel = mypageViewModel
        val writeAdapter = MyWriteAdapter()
        binding.rcvMywrite.apply {
            adapter = writeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        mypageViewModel.setDummyWrite()
        mypageViewModel.mypageWriteData.observe(viewLifecycleOwner) { it ->
            it.let { writeAdapter.replaceWriteList(it) }
        }
        mypageViewModel.isWriteFilterClicked.observe(viewLifecycleOwner) {
            filterClickListener(it)
        }
        mypageViewModel.mywriteFilter.observe(viewLifecycleOwner) {
            getSheetDataListener(it)
        }
        return binding.root
    }

    private fun getSheetDataListener(filter: MyWriteFilter) {
        Toast.makeText(context, filter.category + "," + filter.range, Toast.LENGTH_SHORT).show()
    }

    private fun filterClickListener(isFilterClicked: Boolean) {
        if (isFilterClicked) {
            val bottomSheetFragment = BottomWriteFragment()
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
            mypageViewModel.scrapFilterOnClickFalse()
        }
    }
}