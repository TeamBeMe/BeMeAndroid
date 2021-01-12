package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentTermBinding
import com.teambeme.beme.signup.viewmodel.SignUpViewModel

class TermFragment : Fragment() {
    private lateinit var binding: FragmentTermBinding
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_term, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        doneButtonClickListener()
        setObserve()
        setCheckBoxListener()
        return binding.root
    }

    private fun setObserve() {
        signUpViewModel.isServiceChecked.observe(viewLifecycleOwner) { value ->
            binding.btnTermDone.isEnabled = value && signUpViewModel.isPersonalChecked.value!!
        }
        signUpViewModel.isPersonalChecked.observe(viewLifecycleOwner) { value ->
            binding.btnTermDone.isEnabled = value && signUpViewModel.isServiceChecked.value!!
        }
    }
    private fun setCheckBoxListener() {
        binding.checkboxTermEssence.setOnCheckedChangeListener { _, isChecked ->
            signUpViewModel.isPersonalChecked.value = isChecked
        }
        binding.checkboxTermService.setOnCheckedChangeListener { _, isChecked ->
            signUpViewModel.isServiceChecked.value = isChecked
        }
    }

    private fun doneButtonClickListener() {
        binding.btnTermDone.setOnClickListener {
            this.findNavController().navigate(R.id.action_termFragment_to_personalInfoFragment)
        }
    }
}