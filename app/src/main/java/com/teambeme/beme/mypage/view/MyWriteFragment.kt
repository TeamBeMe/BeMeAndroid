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
import androidx.recyclerview.widget.RecyclerView
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyWriteBinding
import com.teambeme.beme.mypage.adapter.MyWriteAdapter
import com.teambeme.beme.mypage.model.MyWriteFilter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyWriteFragment : Fragment() {
    private lateinit var binding: FragmentMyWriteBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    private lateinit var writeAdapter: MyWriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_write, container, false)
        binding.mpViewModel = mypageViewModel
        setAdapter()
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
        setClickListenerForPlusData(binding)
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

    private fun setAdapter() {
        writeAdapter = MyWriteAdapter()
        binding.rcvMywrite.apply {
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lastVisiblePosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val itemTotalCount = adapter!!.itemCount - 1
                    if (lastVisiblePosition == itemTotalCount) {
                        binding.btnWriteShowmore.visibility = View.VISIBLE
                    } else {
                        binding.btnWriteShowmore.visibility = View.GONE
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
            adapter = writeAdapter
        }
    }

    private fun setClickListenerForPlusData(binding: FragmentMyWriteBinding) {
        binding.btnWriteShowmore.setOnClickListener {
            binding.btnWriteShowmore.visibility = View.GONE
            mypageViewModel.addDummyWrite()
            writeAdapter.submitList(mypageViewModel.mypageWriteData.value?.toMutableList())
        }
    }
}