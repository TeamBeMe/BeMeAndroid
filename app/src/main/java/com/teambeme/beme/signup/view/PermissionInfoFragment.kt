package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentPermissionInfoBinding

class PermissionInfoFragment : Fragment() {
    private var _binding: FragmentPermissionInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermissionInfoBinding.inflate(inflater, container, false)
        binding.btnPermissionCheck.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_permissionInfoFragment_to_termFragment)
        }
        return binding.root
    }
}