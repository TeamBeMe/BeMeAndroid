package com.teambeme.beme.signup.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentImageChooseBinding
import com.teambeme.beme.signup.viewmodel.SignUpViewModel

class ImageChooseFragment : Fragment() {
    private lateinit var binding: FragmentImageChooseBinding
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_image_choose, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnImageChooseDone.setOnClickListener {
            requireActivity().finish()
        }

        signUpViewModel.signUpUserInfo.observe(viewLifecycleOwner) { userInfo ->
            if (userInfo != null) {
                if (userInfo.success) {
                    Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("SignUp", userInfo.message)
                }
            } else {
                Toast.makeText(requireContext(), "회원가입이 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}