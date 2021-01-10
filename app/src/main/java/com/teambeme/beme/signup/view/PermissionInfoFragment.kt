package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return binding.root
    }
}