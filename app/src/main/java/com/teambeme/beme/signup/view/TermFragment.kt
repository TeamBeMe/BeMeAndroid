package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentTermBinding

class TermFragment : Fragment() {
    private lateinit var binding: FragmentTermBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_term, container, false)
        binding.btnTermDone.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_termFragment_to_personalInfoFragment)
        }
        return binding.root
    }
}