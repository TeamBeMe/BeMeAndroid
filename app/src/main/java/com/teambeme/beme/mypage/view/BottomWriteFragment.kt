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
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class BottomWriteFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomWriteBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    private var range: String = ""
    private var category: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_write, container, false)
        binding.mpViewModel = mypageViewModel
        binding.txtWritesheetApply.setOnClickListener {
            applyFilter()
            dismiss()
        }
        binding.chipGroupRange.setOnCheckedChangeListener { group, checkedId ->
            setRangeOnCheckedListner(checkedId)
        }
        binding.chipGroupWriteCategori.setOnCheckedChangeListener { group, checkedId ->
            setWriteOnCheckedListener(checkedId)
        }

        return binding.root
    }

    private fun setWriteOnCheckedListener(checkId: Int) {
        when (checkId) {
            R.id.chip_write_1 -> category = binding.chipWrite1.text.toString()
            R.id.chip_write_2 -> category = binding.chipWrite2.text.toString()
            R.id.chip_write_3 -> category = binding.chipWrite3.text.toString()
            R.id.chip_write_4 -> category = binding.chipWrite4.text.toString()
            R.id.chip_write_5 -> category = binding.chipWrite5.text.toString()
            R.id.chip_write_6 -> category = binding.chipWrite6.text.toString()
            R.id.chip_write_7 -> category = binding.chipWrite7.text.toString()
            R.id.chip_write_8 -> category = binding.chipWrite8.text.toString()
        }
    }

    private fun setRangeOnCheckedListner(checkId: Int) {
        when (checkId) {
            R.id.chip_range_1 -> range = binding.chipRange1.text.toString()
            R.id.chip_range_2 -> range = binding.chipRange2.text.toString()
            R.id.chip_range_3 -> range = binding.chipRange3.text.toString()
        }
    }

    private fun applyFilter() {
        if (category != "" || range != "") {
            mypageViewModel.setWriteFilter(range, category)
        }
    }
}