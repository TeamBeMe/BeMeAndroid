package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomScrapBinding
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class BottomScrapFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomScrapBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    private var category: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_scrap, container, false)
        binding.mpViewModel = mypageViewModel
        binding.txtScrapsheetApply.setOnClickListener {
            applyFilter()
            dismiss()
        }
        binding.chipGroupScrapCategori.setOnCheckedChangeListener { group, checkedId ->
            setCategoryOnCheckedListener(checkedId)
        }

        return binding.root
    }

    private fun setCategoryOnCheckedListener(checkId: Int) {
        when (checkId) {
            R.id.chip_scrap_1 -> category = binding.chipScrap1.text.toString()
            R.id.chip_scrap_2 -> category = binding.chipScrap2.text.toString()
            R.id.chip_scrap_3 -> category = binding.chipScrap3.text.toString()
            R.id.chip_scrap_4 -> category = binding.chipScrap4.text.toString()
            R.id.chip_scrap_5 -> category = binding.chipScrap5.text.toString()
            R.id.chip_scrap_6 -> category = binding.chipScrap6.text.toString()
            R.id.chip_scrap_7 -> category = binding.chipScrap7.text.toString()
            R.id.chip_scrap_8 -> category = binding.chipScrap8.text.toString()
        }
    }

    private fun applyFilter() {
        if (category != "") {
            mypageViewModel.setScrapFilter(category)
        }
    }
}