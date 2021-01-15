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
        binding.tbOtherbottomReport.setOnClickListener {
            dismiss()
            sendEmail()
        }
        return binding.root
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("teambeme@naver.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "BeMe 유저 신고 ")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "1. 신고 유형  사유 (상업적 광고 및 판매, 음란물/불건전한 대화, 욕설 및 비하, 도배, 부적절한 프로필 이미지, 기타 사유) :  \n" +
                    "2. 신고할 유저의 닉네임 :\n" +
                    "\n" +
                    "신고하신 사항은 BeMe팀이 신속하게 처리하겠습니다. 감사합니다 :)"
        )
        intent.type = "message/rfc822"
        startActivity(intent)
    }
}