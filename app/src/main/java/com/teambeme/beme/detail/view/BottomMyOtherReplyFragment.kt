package com.teambeme.beme.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomMyOtherBinding
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class BottomMyOtherReplyFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomMyOtherBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_my_other, container, false)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        binding.tbMyotherbottomBlock.setOnClickListener {
            dismiss()
        }
        binding.tbMyotherbottomDelete.setOnClickListener {
            dismiss()
        }
        binding.tbMyotherbottomReport.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    /*private fun setCategoryOnCheckedListener(checkId: Int) {
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
    }*/
}