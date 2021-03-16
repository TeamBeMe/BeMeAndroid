package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.data.remote.datasource.MyPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentMyWriteBinding
import com.teambeme.beme.mypage.adapter.MyWriteAdapter
import com.teambeme.beme.mypage.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.view.BottomWriteFragment.Companion.WRITE_FILTER
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory
import com.teambeme.beme.util.recordClickEvent

class MyWriteFragment : BindingFragment<FragmentMyWriteBinding>(R.layout.fragment_my_write) {
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.myPageViewModel = mypageViewModel
        binding.lifecycleOwner = this
        mypageViewModel.initMyAnswer()
        setMyWriteAdapter()
        setMyWriteObserve()
        setIsWriteFilterClickedObserve()
        setMyWriteFilterObserve()
        setIsAnswerMaxObserve()
        setPublicPositionObserve()
        setIsAnswerEmptyObserve()
        setImgWriteFilterClickListener()
        setSearchView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mypageViewModel.initPage()
        mypageViewModel.getMyAnswer()
    }

    private fun setMyWriteAdapter() {
        val writeAdapter = MyWriteAdapter(mypageViewModel)
        binding.rcvMywrite.adapter = writeAdapter
    }

    private fun setMyWriteObserve() {
        mypageViewModel.mypageWriteData.observe(viewLifecycleOwner) { myPageWriteData ->
            myPageWriteData.let {
                if (binding.rcvMywrite.adapter != null) with(binding.rcvMywrite.adapter as MyWriteAdapter) {
                    submitList(myPageWriteData)
                }
            }
        }
    }

    private fun setIsWriteFilterClickedObserve() {
        mypageViewModel.isWriteFilterClicked.observe(viewLifecycleOwner) { isWriteFilterClicked ->
            isWriteFilterClicked.let {
                if (isWriteFilterClicked) {
                    val bottomSheetFragment = BottomWriteFragment(WRITE_FILTER)
                    bottomSheetFragment.show(
                        requireActivity().supportFragmentManager,
                        bottomSheetFragment.tag
                    )
                    mypageViewModel.writeFilterOnClickFalse()
                }
            }
        }
    }

    private fun setMyWriteFilterObserve() {
        mypageViewModel.mywriteFilter.observe(viewLifecycleOwner) { myWriteFilter ->
            myWriteFilter.let {
                mypageViewModel.initPage()
                mypageViewModel.getMyAnswer()
            }
        }
    }

    private fun setIsAnswerMaxObserve() {
        mypageViewModel.isAnswerMax.observe(viewLifecycleOwner) { isAnswerMax ->
            isAnswerMax.let {
                if (isAnswerMax) {
                    binding.btnWriteShowmore.visibility = View.GONE
                } else {
                    binding.btnWriteShowmore.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setPublicPositionObserve() {
        mypageViewModel.publicPosition.observe(viewLifecycleOwner) { publicPosition ->
            publicPosition.let {
                mypageViewModel.putPublic()
            }
        }
    }

    private fun setIsAnswerEmptyObserve() {
        mypageViewModel.isAnswerEmpty.observe(viewLifecycleOwner) { isAnswerEmpty ->
            isAnswerEmpty.let {
                if (isAnswerEmpty) {
                    binding.rcvMywrite.visibility = View.GONE
                    binding.constraintWriteEmpty.visibility = View.VISIBLE
                } else {
                    binding.constraintWriteEmpty.visibility = View.GONE
                    binding.rcvMywrite.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setImgWriteFilterClickListener() {
        binding.imgWriteFilter.setOnClickListener {
            recordClickEvent("BUTTON", "CLICK_FILTER_MYPAGE")
            mypageViewModel.writeFilterOnClick()
        }
    }

    private fun setSearchView() {
        binding.searchViewWriteSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                Log.d("Search", newText ?: "hyunwoo")
                mypageViewModel.initPage()
                mypageViewModel.getMyAnswer()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInputText = newText ?: ""
                mypageViewModel.setMyQuery(newText.toString())
                if (userInputText.count() == 0) {
                    mypageViewModel.initPage()
                    mypageViewModel.deleteMyQuery()
                    mypageViewModel.getMyAnswer()
                }
                return false
            }
        })
    }

    fun setScrollToTop() {
        view?.let { binding.nestedScrollViewMywrite.smoothScrollTo(0, it.top) }
    }
}