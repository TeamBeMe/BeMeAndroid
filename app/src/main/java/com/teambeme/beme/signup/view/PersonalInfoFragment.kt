package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {
    private lateinit var binding: FragmentPersonalInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_personal_info, container, false)
        binding.btnPersonalDone.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_personalInfoFragment_to_imageChooseFragment)
        }
        return binding.root
    }
}