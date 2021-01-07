package com.teambeme.beme.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.tbMybottomFix.setOnClickListener {
            dismiss()
        }
        binding.tbMybottomDelete.setOnClickListener {
            if (detailViewModel.childposition.value != -1) {
                detailViewModel.deleteDummyReply(
                    detailViewModel.position.value!!,
                    detailViewModel.childposition.value!!
                )
            } else {
                detailViewModel.deleteDummyParentReply(detailViewModel.position.value!!)
            }
            dismiss()
        }

        return binding.root
    }
}