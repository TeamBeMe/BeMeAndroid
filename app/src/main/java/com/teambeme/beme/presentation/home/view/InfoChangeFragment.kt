package com.teambeme.beme.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.databinding.FragmentInfoChangeBinding

class InfoChangeFragment(private val infoChangeClickListener: InfoChangeClickListener) :
    BottomSheetDialogFragment() {
    private var _binding: FragmentInfoChangeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            linearHomeEdit.setOnClickListener {
                infoChangeClickListener.modifyAnswer()
                dismiss()
            }
            linearHomeDelete.setOnClickListener {
                infoChangeClickListener.deleteAnswer()
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface InfoChangeClickListener {
        fun modifyAnswer()
        fun deleteAnswer()
    }
}
