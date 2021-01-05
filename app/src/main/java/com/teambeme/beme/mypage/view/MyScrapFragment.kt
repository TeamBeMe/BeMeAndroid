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
import com.teambeme.beme.databinding.FragmentMyScrapBinding
import com.teambeme.beme.mypage.adapter.MyScrapAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyScrapFragment : Fragment() {
    private lateinit var binding: FragmentMyScrapBinding

    // private lateinit var scrapAdapter: MyScrapAdapter
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_scrap, container, false)
        binding.lifecycleOwner = this
        binding.myPageViewModel = mypageViewModel
        val scrapAdapter = MyScrapAdapter()
        setAdapter(scrapAdapter)
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
        setClickListenerForPlusData(binding, scrapAdapter)
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

    private fun setAdapter(scrapAdapter: MyScrapAdapter) {
        binding.rcvMyscrap.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lastVisiblePosition =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val itemTotalCount = adapter!!.itemCount - 1
                    if (lastVisiblePosition == itemTotalCount) {
                        binding.btnScrapShowmore.visibility = View.VISIBLE
                    } else {
                        binding.btnScrapShowmore.visibility = View.GONE
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
            adapter = scrapAdapter
        }
    }

    private fun getSheetDataListener(category: String) {
        Toast.makeText(requireContext(), category, Toast.LENGTH_SHORT).show()
    }

    private fun setClickListenerForPlusData(
        binding: FragmentMyScrapBinding,
        scrapAdapter: MyScrapAdapter
    ) {
        binding.btnScrapShowmore.setOnClickListener {
            binding.btnScrapShowmore.visibility = View.GONE
            mypageViewModel.addDummyScrap()
            scrapAdapter.submitList(mypageViewModel.mypageScrapData.value?.toMutableList())
        }
    }
}