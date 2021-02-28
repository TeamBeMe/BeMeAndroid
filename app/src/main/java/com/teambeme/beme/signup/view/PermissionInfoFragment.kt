package com.teambeme.beme.signup.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentPermissionInfoBinding
import com.teambeme.beme.util.recordClickEvent

class PermissionInfoFragment : Fragment() {
    private var _binding: FragmentPermissionInfoBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "SignUpActivity")
            param(FirebaseAnalytics.Param.SCREEN_NAME, "GUIDANCE_SIGN")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermissionInfoBinding.inflate(inflater, container, false)
        binding.btnPermissionCheck.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_permissionInfoFragment_to_termFragment)
        }
        binding.btnPermissionBack.setOnClickListener {
            recordClickEvent("BACK_PRESS", "OUT_GUIDANCE_SIGN")
            requireActivity().finish()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}