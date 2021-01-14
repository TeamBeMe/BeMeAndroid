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
import com.teambeme.beme.databinding.FragmentMyWriteBinding
import com.teambeme.beme.mypage.adapter.MyWriteAdapter
import com.teambeme.beme.mypage.model.MyWriteFilter
import com.teambeme.beme.mypage.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.view.BottomWriteFragment.Companion.WRITE_FILTER
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory

class MyWriteFragment : Fragment() {
    private lateinit var binding: FragmentMyWriteBinding
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_write, container, false)
        binding.lifecycleOwner = this
        binding.myPageViewModel = mypageViewModel
        val writeAdapter = MyWriteAdapter(mypageViewModel)
        setAdapter(writeAdapter)
        mypageViewModel.initMyAnswer()
        mypageViewModel.mypageWriteData.observe(viewLifecycleOwner) { it ->
            it.let { writeAdapter.replaceWriteList(it) }
        }
        mypageViewModel.isWriteFilterClicked.observe(viewLifecycleOwner) {
            filterClickListener(it)
        }
        mypageViewModel.mywriteFilter.observe(viewLifecycleOwner) {
            getSheetDataListener(it)
        }
        mypageViewModel.isAnswerMax.observe(viewLifecycleOwner) {
            isMaxListener(it)
        }
        setClickListenerForPlusData(binding, writeAdapter)
        mypageViewModel.publicPosition.observe(viewLifecycleOwner) {
            isPublicListener()
        }
        mypageViewModel.isAnswerEmpty.observe(viewLifecycleOwner) {
            isEmptyListener(it)
        }
        binding.editxtWriteSearch.setOnEditorActionListener { view, i, event ->
            mypageViewModel.initPage()
            mypageViewModel.setMyQuery(binding.editxtWriteSearch.text.toString())
            mypageViewModel.getMyAnswer()
            hideKeyboard()
            true
        }
        return binding.root
    }

    override fun onResume() {
        mypageViewModel.initPage()
        mypageViewModel.getMyAnswer()
        super.onResume()
    }

    private fun hideKeyboard() {
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.editxtWriteSearch.windowToken, 0)
    }

    private fun isEmptyListener(isEmpty: Boolean) {
        if (isEmpty) {
            binding.rcvMywrite.visibility = View.GONE
            binding.constraintWriteEmpty.visibility = View.VISIBLE
        } else {
            binding.constraintWriteEmpty.visibility = View.GONE
            binding.rcvMywrite.visibility = View.VISIBLE
        }
    }

    private fun isPublicListener() {
        mypageViewModel.putPublic()
    }

    private fun isMaxListener(isMax: Boolean) {
        if (isMax) {
            binding.btnWriteShowmore.visibility = View.GONE
        } else {
            binding.btnWriteShowmore.visibility = View.VISIBLE
        }
    }

    private fun getSheetDataListener(filter: MyWriteFilter) {
        mypageViewModel.initPage()
        mypageViewModel.getMyAnswer()
    }

    private fun filterClickListener(isFilterClicked: Boolean) {
        if (isFilterClicked) {
            val bottomSheetFragment = BottomWriteFragment(WRITE_FILTER)
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
            mypageViewModel.writeFilterOnClickFalse()
        }
    }

    private fun setAdapter(writeAdapter: MyWriteAdapter) {
        binding.rcvMywrite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = writeAdapter
        }
    }

    private fun setClickListenerForPlusData(
        binding: FragmentMyWriteBinding,
        writeAdapter: MyWriteAdapter
    ) {
        binding.btnWriteShowmore.setOnClickListener {
            binding.btnWriteShowmore.visibility = View.GONE
            mypageViewModel.getMyAnswer()
            writeAdapter.submitList(mypageViewModel.mypageWriteData.value?.toMutableList())
        }
    }
}