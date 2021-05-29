package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomWriteBinding
import com.teambeme.beme.mypage.model.CategoryFilter
import com.teambeme.beme.mypage.model.PublicRange
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.util.recordClickEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomWriteFragment(private val filter: Boolean) : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomWriteBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    private var range: String? = null
    private var category: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_write, container, false)
        binding.myPageViewModel = mypageViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.txtWritesheetApply.setOnClickListener {
            if (filter) {
                applyWriteFilter()
            } else {
                applyScrapFilter()
            }
            dismiss()
        }
        binding.chipGroupRange.setOnCheckedChangeListener { group, checkedId ->
            recordClickEvent("BUTTON", PublicRange.asItemId(checkedId))
            setRangeOnCheckedListner(checkedId)
        }
        binding.chipGroupWriteCategori.setOnCheckedChangeListener { group, checkedId ->
            recordClickEvent("BUTTON", CategoryFilter.asItemId(checkedId))
            setWriteOnCheckedListener(checkedId)
        }
        return binding.root
    }

    private fun setWriteOnCheckedListener(checkId: Int) {
        when (checkId) {
            R.id.chip_write_1 -> category = CATEGORY_VALUE
            R.id.chip_write_2 -> category = CATEGORY_RELATION
            R.id.chip_write_3 -> category = CATEGORY_LOVE
            R.id.chip_write_4 -> category = CATEGORY_DAILY
            R.id.chip_write_5 -> category = CATEGORY_ME
            R.id.chip_write_6 -> category = CATEGORY_STORY
            else -> category = CATEGORY_ALL
        }
    }

    private fun setRangeOnCheckedListner(checkId: Int) {
        when (checkId) {
            R.id.chip_range_1 -> range = null
            R.id.chip_range_2 -> range = "public"
            R.id.chip_range_3 -> range = "unpublic"
            else -> range = null
        }
    }

    private fun applyWriteFilter() {
        mypageViewModel.setWriteFilter(range, category)
    }

    private fun applyScrapFilter() {
        mypageViewModel.setScrapFilter(range, category)
    }

    companion object {
        private val CATEGORY_ALL = null
        private val CATEGORY_VALUE = 1
        private val CATEGORY_RELATION = 2
        private val CATEGORY_LOVE = 3
        private val CATEGORY_DAILY = 4
        private val CATEGORY_ME = 5
        private val CATEGORY_STORY = 6
        val WRITE_FILTER = true
        val SCRAP_FILTER = false
    }
}
