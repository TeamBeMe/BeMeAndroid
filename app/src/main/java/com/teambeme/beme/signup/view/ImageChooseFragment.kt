package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentImageChooseBinding

class ImageChooseFragment : BindingFragment<FragmentImageChooseBinding>(R.layout.fragment_image_choose) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}