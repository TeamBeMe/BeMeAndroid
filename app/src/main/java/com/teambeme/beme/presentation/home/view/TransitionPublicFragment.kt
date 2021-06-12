package com.teambeme.beme.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentTransitionPublicBinding

class TransitionPublicFragment(
    private val isPublic: Int,
    private val clickListener: ChangePublicClickListener
) : DialogFragment() {
    private var _binding: FragmentTransitionPublicBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.home_answer_suggestion_background)
        _binding = FragmentTransitionPublicBinding.inflate(
            inflater,
            container,
            false
        )
        setDialogText()
        binding.txtHomeTransitionCancel.setOnClickListener { dismiss() }
        binding.txtHomeTransitionApprove.setOnClickListener {
            clickListener.onClick()
            dismiss()
        }
        return binding.root
    }

    private fun setDialogText() {
        when (isPublic) {
            PRIVATE -> {
                binding.txtHomeTransitionPublic.text = "공개 질문으로 전환하시겠어요?"
            }
            else -> {
                binding.txtHomeTransitionPublic.text = "비공개 질문으로 전환하시겠어요?"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.8).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ChangePublicClickListener {
        fun onClick()
    }

    companion object {
        private const val PUBLIC = 1
        private const val PRIVATE = 0
    }
}
