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
        binding.tbMyotherbottomDelete.setOnClickListener {
            deleteReply()
        }
        binding.tbMyotherbottomReport.setOnClickListener {
            sendEmail()
            dismiss()
        }

        return binding.root
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("teambeme@naver.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "BeMe 유저 신고 ")
        intent.putExtra(
            Intent.EXTRA_TEXT, "1. 문의 유형 ( 문의, 버그 제보, 탈퇴하기, 기타) : \n" +
                    "2. 회원 닉네임 (필요시 기입) :\n" +
                    "3. 문의 내용 :\n" +
                    "\n" +
                    "문의하신 사항은 BeMe팀이 신속하게 처리하겠습니다. 감사합니다 :)"
        )
        intent.type = "message/rfc822"
        startActivity(intent)
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
}