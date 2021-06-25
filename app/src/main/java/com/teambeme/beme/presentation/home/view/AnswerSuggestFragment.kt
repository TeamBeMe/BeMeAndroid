package com.teambeme.beme.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentAnswerSuggestBinding

class AnswerSuggestFragment : DialogFragment() {
    private var _binding: FragmentAnswerSuggestBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.home_answer_suggestion_background)
        _binding = FragmentAnswerSuggestBinding.inflate(
            inflater,
            container,
            false
        )
        binding.txtHomeDialogCancel.setOnClickListener { dismiss() }
        binding.txtHomeDialogApprove.setOnClickListener { dismiss() }
        return binding.root
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
}
