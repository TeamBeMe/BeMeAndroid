package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentMyScrapBinding
import com.teambeme.beme.mypage.adapter.MyScrapAdapter
import com.teambeme.beme.mypage.view.BottomWriteFragment.Companion.SCRAP_FILTER
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.util.RecordScreenUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyScrapFragment : BindingFragment<FragmentMyScrapBinding>(R.layout.fragment_my_scrap) {
    private val mypageViewModel: MyPageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = mypageViewModel
        setMyScrapAdapter()
        setMyPageScrapDataObserve()
        setIsScrapFilterClickedObserve()
        setScrapFilterObserve()
        setIsScrapMaxObserve()
        setIsScrapEmptyObserve()
        setSearchView()
        RecordScreenUtil.recordScreen("MyPage_MyScrapFragment")
        setScrollToTop()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        RecordScreenUtil.recordScreen("MyPage_MyScrapFragment")
        mypageViewModel.getMyScrap(mypageViewModel.scrapTempPage)
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
                mypageViewModel.getMyScrap(mypageViewModel.scrapTempPage)
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
                mypageViewModel.getMyScrap(mypageViewModel.scrapTempPage)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInputText = newText ?: ""
                mypageViewModel.setScrapQuery(newText.toString())
                if (userInputText.count() == 0) {
                    mypageViewModel.initScrapPage()
                    mypageViewModel.deleteScrapQuery()
                    mypageViewModel.getMyScrap(mypageViewModel.scrapTempPage)
                }
                return false
            }
        })
    }

    private fun setScrollToTop() {
        mypageViewModel.scrapScrollUp.observe(viewLifecycleOwner) {
            binding.nestedScrollViewMyscrap.apply { smoothScrollTo(0, this.top) }
        }
    }
}