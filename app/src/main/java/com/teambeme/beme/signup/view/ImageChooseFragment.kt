package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentImageChooseBinding

class ImageChooseFragment : Fragment() {
    private lateinit var binding: FragmentImageChooseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_choose, container, false)
        binding.btnImageChooseDone.setOnClickListener {
            requireActivity().finish()
        }
        return binding.root
    }
}