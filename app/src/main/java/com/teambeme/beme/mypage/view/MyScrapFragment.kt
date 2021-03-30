package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.data.remote.datasource.MyPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.FragmentMyScrapBinding
import com.teambeme.beme.mypage.adapter.MyScrapAdapter
import com.teambeme.beme.mypage.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.view.BottomWriteFragment.Companion.SCRAP_FILTER
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory
import com.teambeme.beme.util.RecordScreenUtil

class MyScrapFragment : BindingFragment<FragmentMyScrapBinding>(R.layout.fragment_my_scrap) {
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = this
        binding.myPageViewModel = mypageViewModel
        mypageViewModel.initScrap()
        setMyScrapAdapter()
        setMyPageScrapDataObserve()
        setIsScrapFilterClickedObserve()
        setScrapFilterObserve()
        setIsScrapMaxObserve()
        setIsScrapEmptyObserve()
        setSearchView()
        RecordScreenUtil.recordScreen("MyPage_MyScrapFragment", "MainActivity")
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        RecordScreenUtil.recordScreen("MyPage_MyScrapFragment", "MainActivity")
        mypageViewModel.initScrapPage()
        mypageViewModel.getMyScrap()
    }

    private fun setMyScrapAdapter() {
        val scrapAdapter = MyScrapAdapter(requireContext())
        binding.rcvMyscrap.adapter = scrapAdapter
    }

    private fun setMyPageScrapDataObserve() {
        mypageViewModel.mypageScrapData.observe(viewLifecycleOwner) { myPageScrapData ->
            myPageScrapData.let {
                if (binding.rcvMyscrap.adapter != null) with(binding.rcvMyscrap.adapter as MyScrapAdapter) {
                    submitList(myPageScrapData)
                }
            }
        }
    }

    private fun setIsScrapFilterClickedObserve() {
        mypageViewModel.isScrapFilterClicked.observe(viewLifecycleOwner) { isScrapFilteredClicked ->
            isScrapFilteredClicked.let {
                if (isScrapFilteredClicked) {
                    val bottomSheetFragment = BottomWriteFragment(SCRAP_FILTER)
                    bottomSheetFragment.show(
                        requireActivity().supportFragmentManager,
                        bottomSheetFragment.tag
                    )
                    mypageViewModel.scrapFilterOnClickFalse()
                }
            }
        }
    }

    private fun setScrapFilterObserve() {
        mypageViewModel.scrapFilter.observe(viewLifecycleOwner) { scrapFilter ->
            scrapFilter.let {
                mypageViewModel.initScrapPage()
                mypageViewModel.getMyScrap()
            }
        }
    }

    private fun setIsScrapMaxObserve() {
        mypageViewModel.isScrapMax.observe(viewLifecycleOwner) { isScrapMax ->
            isScrapMax.let {
                if (isScrapMax) {
                    binding.btnScrapShowmore.visibility = View.GONE
                } else {
                    binding.btnScrapShowmore.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setIsScrapEmptyObserve() {
        mypageViewModel.isScrapEmpty.observe(viewLifecycleOwner) { isScrapEmpty ->
            isScrapEmpty.let {
                if (isScrapEmpty) {
                    binding.rcvMyscrap.visibility = View.GONE
                    binding.constraintScrapEmpty.visibility = View.VISIBLE
                } else {
                    binding.constraintScrapEmpty.visibility = View.GONE
                    binding.rcvMyscrap.visibility = View.VISIBLE
                }
            }
        }
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

    fun setScrollToTop() {
        view?.let { binding.nestedScrollViewMyscrap.smoothScrollTo(0, it.top) }
    }
}