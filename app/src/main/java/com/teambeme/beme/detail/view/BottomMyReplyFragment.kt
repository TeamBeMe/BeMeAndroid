package com.teambeme.beme.detail.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.databinding.ItemBottomMyBinding
import com.teambeme.beme.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        if (child) {
            onAnswerListener()
        } else {
            onReplyListener()
        }
        return binding.root
    }

    private fun onReplyListener() {
        binding.tbMybottomFix.setOnClickListener {
            changeReply()
        }
        binding.tbMybottomDelete.setOnClickListener {
            deleteReply()
        }
    }

    private fun onAnswerListener() {
        binding.txtMybottomFix.text = "내글 수정"
        binding.txtMybottomDelete.text = "내글 삭제"
        binding.tbMybottomFix.setOnClickListener {
            changeAnswer()
        }
        binding.tbMybottomDelete.setOnClickListener {
            deleteAnswer()
        }
    }

    private fun deleteAnswer() {
        detailViewModel.deleteAnswer()
        dismiss()
    }

    private fun changeAnswer() {
        detailViewModel.setIntentData()
        val intent = Intent(requireContext(), AnswerActivity::class.java)
        intent.putExtra("intentAnswerData", detailViewModel.intentData.value)
        intent.putExtra("isChange", 100)
        startActivity(intent)
        dismiss()
    }

    private fun deleteReply() {
        if (detailViewModel.childposition.value != -1) {
            detailViewModel.deleteChildReply(
                detailViewModel.position.value!!,
                detailViewModel.childposition.value!!
            )
        } else {
            detailViewModel.deleteParentReply(detailViewModel.position.value!!)
        }
        dismiss()
    }

    private fun changeReply() {
        if (detailViewModel.childposition.value != -1) {
            detailViewModel.getChildReplyComment()
            detailViewModel.setChildChangeClicked()
        } else {
            detailViewModel.getParentReplyComment()
            detailViewModel.setChangeClicked()
        }
        dismiss()
    }
}
