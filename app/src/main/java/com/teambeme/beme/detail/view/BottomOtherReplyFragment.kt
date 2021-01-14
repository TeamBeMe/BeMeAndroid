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
import com.teambeme.beme.databinding.ItemBottomOtherBinding
import com.teambeme.beme.detail.viewmodel.DetailViewModel

class BottomOtherReplyFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomOtherBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_other, container, false)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        binding.tbOtherbottomReport.setOnClickListener {
            dismiss()
            sendEmail()
        }
        return binding.root
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("qodhrkawk@naver.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "신고하기")
        intent.putExtra(Intent.EXTRA_TEXT, "신고 사유를 적어주세요")
        intent.type = "message/rfc822"
        startActivity(intent)
    }
}