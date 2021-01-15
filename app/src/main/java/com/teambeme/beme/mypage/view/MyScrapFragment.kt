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
import com.teambeme.beme.data.remote.datasource.MyPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentMyScrapBinding
import com.teambeme.beme.mypage.adapter.MyScrapAdapter
import com.teambeme.beme.mypage.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.view.BottomWriteFragment.Companion.SCRAP_FILTER
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory

class MyScrapFragment : Fragment() {
    private lateinit var binding: FragmentMyScrapBinding
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_scrap, container, false)
        binding.lifecycleOwner = this
        binding.myPageViewModel = mypageViewModel
        val scrapAdapter = MyScrapAdapter()
        setAdapter(scrapAdapter)
        mypageViewModel.initScrap()
        mypageViewModel.mypageScrapData.observe(viewLifecycleOwner) { it ->
            it.let { scrapAdapter.submitList(it) }
        }
        mypageViewModel.isScrapFilterClicked.observe(viewLifecycleOwner) {
            filterClickListener(it)
        }
        mypageViewModel.scrapFilter.observe(viewLifecycleOwner) {
            getSheetDataListener()
        }
        setClickListenerForPlusData(binding, scrapAdapter)
        mypageViewModel.isScrapMax.observe(viewLifecycleOwner) {
            isMaxListener(it)
        }
        mypageViewModel.isScrapEmpty.observe(viewLifecycleOwner) {
            isEmptyListener(it)
        }
        setSearchView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mypageViewModel.initScrapPage()
        mypageViewModel.getMyScrap()
    }

    private fun setSearchView() {
        binding.searchViewScrapSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                mypageViewModel.initScrapPage()
                mypageViewModel.getMyScrap()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInputText = newText ?: ""
                mypageViewModel.setScrapQuery(newText.toString())
                if (userInputText.count() == 0) {
                    mypageViewModel.initScrapPage()
                    mypageViewModel.deleteScrapQuery()
                    mypageViewModel.getMyScrap()
                }
                return false
            }
        })
    }

    private fun isEmptyListener(isEmpty: Boolean) {
        if (isEmpty) {
            binding.rcvMyscrap.visibility = View.GONE
            binding.constraintScrapEmpty.visibility = View.VISIBLE
        } else {
            binding.constraintScrapEmpty.visibility = View.GONE
            binding.rcvMyscrap.visibility = View.VISIBLE
        }
    }

    private fun filterClickListener(isFilterClicked: Boolean) {
        if (isFilterClicked) {
            val bottomSheetFragment = BottomWriteFragment(SCRAP_FILTER)
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
            adapter = scrapAdapter
        }
    }

    private fun getSheetDataListener() {
        mypageViewModel.initScrapPage()
        mypageViewModel.getMyScrap()
    }

    private fun isMaxListener(isMax: Boolean) {
        if (isMax) {
            binding.btnScrapShowmore.visibility = View.VISIBLE
        } else {
            binding.btnScrapShowmore.visibility = View.GONE
        }
    }

    private fun setClickListenerForPlusData(
        binding: FragmentMyScrapBinding,
        scrapAdapter: MyScrapAdapter
    ) {
        binding.btnScrapShowmore.setOnClickListener {
            binding.btnScrapShowmore.visibility = View.GONE
            mypageViewModel.getMyScrap()
            scrapAdapter.submitList(mypageViewModel.mypageScrapData.value?.toMutableList())
        }
    }
}