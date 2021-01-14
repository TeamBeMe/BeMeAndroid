package com.teambeme.beme.mypage.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        binding.editxtScrapSearch.setOnEditorActionListener { view, i, event ->
            mypageViewModel.initScrapPage()
            mypageViewModel.setScrapQuery(binding.editxtScrapSearch.text.toString())
            mypageViewModel.getMyScrap()
            hideKeyboard()
            true
        }
        mypageViewModel.isScrapEmpty.observe(viewLifecycleOwner) {
            isEmptyListener(it)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mypageViewModel.initScrapPage()
        mypageViewModel.getMyScrap()
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.editxtScrapSearch.windowToken, 0)
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
            binding.btnScrapShowmore.visibility = View.GONE
        } else {
            binding.btnScrapShowmore.visibility = View.VISIBLE
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