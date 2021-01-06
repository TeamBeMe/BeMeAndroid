package com.teambeme.beme.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomMyBinding
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class BottomMyReplyFragment(private val child: Boolean) : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomMyBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_my, container, false)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        Toast.makeText(context, "${detailViewModel.position.value}a", Toast.LENGTH_SHORT).show()
        binding.tbMybottomFix.setOnClickListener {
            dismiss()
        }
        binding.tbMybottomDelete.setOnClickListener {
            if (child) {
            } else {
                detailViewModel.deleteDummyParentReply(detailViewModel.position.value!!)
            }
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