package com.teambeme.beme.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentPersonalInfoBinding
import com.teambeme.beme.signup.viewmodel.SignUpViewModel

class PersonalInfoFragment : Fragment() {
    private lateinit var binding: FragmentPersonalInfoBinding
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_personal_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.signUpViewModel = signUpViewModel
        setDoubleCheckListener()
        setDoneButtonClickListener()
        setObserve()
        return binding.root
    }

    private fun setDoubleCheckListener() {
        signUpViewModel.nickDoubleCheck.observe(viewLifecycleOwner) { checkInfo ->
            if (signUpViewModel.isNickNameValidated.value!!) {
                if (checkInfo.success) {
                    if (!checkInfo.data.nicknameExist) {
                        fixNickName()
                    } else {
                        Toast.makeText(requireContext(), "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(requireContext(), "서버통신이 원활하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fixNickName() {
        Toast.makeText(
            requireContext(),
            "닉네임이 ${signUpViewModel.userNickName.value!!}로 설정되었습니다.",
            Toast.LENGTH_SHORT
        ).show()
        binding.apply {
            edittxtPersonalNickname.isEnabled = false
            edittxtPersonalNickname.setTextColor(resources.getColor(R.color.signup_disabled, null))
            btnPersonalNicknameDoubleCheck.visibility = View.GONE
            txtPersonalNicknameCheck.apply {
                text = "사용 가능한 닉네임입니다, 중복확인을 해주세요"
                setTextColor(resources.getColor(R.color.signup_term_blue, null))
            }
        }
    }

    private fun setObserve() {
        signUpViewModel.userEmail.observe(viewLifecycleOwner) { email ->
            if (REGEX_EMAIL.matches(email)) {
                signUpViewModel.emailValidated()
            } else {
                signUpViewModel.emailNotValidated()
            }
        }
        signUpViewModel.userNickName.observe(viewLifecycleOwner) { nickName ->
            if (nickName.isEmpty()) {
                binding.imgPersonalNicknameCheck.setImageResource(R.drawable.ic_personal_check_gray)
                binding.txtPersonalNicknameCheck.text = "영문, 숫자로 5자 이상 20자 이내로 입력해주세요."
                binding.txtPersonalNicknameCheck.setTextColor(
                    resources.getColor(
                        R.color.signup_personal_check,
                        null
                    )
                )
                signUpViewModel.nickNameNotValidated()
            } else if (!nickName.isLettersOrDigits() || !nickNameLengthValidation(nickName)) {
                binding.imgPersonalNicknameCheck.setImageResource(R.drawable.ic_personal_check_red)
                binding.txtPersonalNicknameCheck.apply {
                    text = "다른 닉네임을 입력해주세요"
                    setTextColor(resources.getColor(R.color.signup_red, null))
                }
                signUpViewModel.nickNameNotValidated()
            } else {
                binding.imgPersonalNicknameCheck.setImageResource(R.drawable.ic_personal_check_blue)
                binding.txtPersonalNicknameCheck.apply {
                    text = "사용 가능한 닉네임입니다, 중복확인을 해주세요"
                    setTextColor(resources.getColor(R.color.signup_term_blue, null))
                }
                signUpViewModel.nickNameValidated()
            }
        }

        signUpViewModel.userPassWord.observe(viewLifecycleOwner) { passWord ->
            if (passWord.isEmpty()) {
                binding.imgPersonalPassword.setImageResource(R.drawable.ic_personal_check_gray)
                binding.txtPersonalPassword.text = "비밀번호는 영문 숫자로 8자 이상 입력해주세요"
                binding.txtPersonalPassword.setTextColor(
                    resources.getColor(
                        R.color.signup_personal_check,
                        null
                    )
                )
                signUpViewModel.passWordNotValidated()
            } else if (!passWord.isAlphabets() || !passWord.isNumbers() || !passWordLengthValidation(
                    passWord
                )
            ) {
                binding.imgPersonalPassword.setImageResource(R.drawable.ic_personal_check_red)
                binding.txtPersonalPassword.apply {
                    text = "8자 이상 20자 이내인 영문과 숫자의 조합이어야 합니다."
                    setTextColor(resources.getColor(R.color.signup_red, null))
                }
                signUpViewModel.passWordNotValidated()
            } else {
                binding.imgPersonalPassword.setImageResource(R.drawable.ic_personal_check_blue)
                binding.txtPersonalPassword.apply {
                    text = "사용 가능한 비밀번호입니다"
                    setTextColor(resources.getColor(R.color.signup_term_blue, null))
                }
                signUpViewModel.passWordValidated()
            }
        }

        signUpViewModel.userPassWordCheck.observe(viewLifecycleOwner) { passWordCheck ->
            if (passWordCheck.isEmpty()) {
                binding.linearPersonalPasswordCheck.visibility = View.INVISIBLE
                signUpViewModel.passWordCheckNotValidated()
            } else {
                binding.linearPersonalPasswordCheck.visibility = View.VISIBLE
                if (passWordCheck != signUpViewModel.userPassWord.value) {
                    binding.imgPersonalPasswordCheck.setImageResource(R.drawable.ic_personal_check_red)
                    binding.txtPersonalPasswordCheck.apply {
                        text = "비밀번호가 일치하지 않습니다"
                        setTextColor(resources.getColor(R.color.signup_red, null))
                    }
                    signUpViewModel.passWordCheckNotValidated()
                } else {
                    binding.imgPersonalPasswordCheck.setImageResource(R.drawable.ic_personal_check_blue)
                    binding.txtPersonalPasswordCheck.apply {
                        text = "비밀번호가 일치합니다"
                        setTextColor(resources.getColor(R.color.signup_term_blue, null))
                    }
                    signUpViewModel.passWordCheckValidated()
                }
            }
        }
    }

    private fun nickNameLengthValidation(nickName: String): Boolean = nickName.length in 5..20
    private fun passWordLengthValidation(nickName: String): Boolean = nickName.length in 8..20

    private fun setDoneButtonClickListener() {
        binding.btnPersonalDone.setOnClickListener { view ->
            if (signUpViewModel.validateAllValues()) {
                view.findNavController()
                    .navigate(R.id.action_personalInfoFragment_to_imageChooseFragment)
            } else {
                makeProblemToastMessage(
                    signUpViewModel.isEmailValidated.value!!,
                    signUpViewModel.isNickNameValidated.value!!,
                    signUpViewModel.isPassWordValidated.value!!,
                    signUpViewModel.isPassWordCheckValidated.value!!
                )
            }
        }
    }

    private fun makeProblemToastMessage(
        email: Boolean,
        nickName: Boolean,
        passWord: Boolean,
        passWordCheck: Boolean
    ) {
        if (!email) {
            Toast.makeText(requireContext(), "이메일을 바르게 적었는지 확인해주세요", Toast.LENGTH_SHORT).show()
        } else if (!nickName) {
            Toast.makeText(requireContext(), "닉네임을 바르게 적었는지 확인해주세요", Toast.LENGTH_SHORT).show()
        } else if (!passWord) {
            Toast.makeText(requireContext(), "비밀번호를 바르게 적었는지 확인해주세요", Toast.LENGTH_SHORT).show()
        } else if (!passWordCheck) {
            Toast.makeText(requireContext(), "비밀번호 중복 검사를 해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun String.isLettersOrDigits(): Boolean {
        return this.filter { it in 'A'..'Z' || it in 'a'..'z' || it in '0'..'9' }
            .length == this.length
    }

    private fun String.isAlphabets(): Boolean {
        return this.any { it in 'A'..'Z' || it in 'a'..'z' }
    }

    private fun String.isNumbers(): Boolean {
        return this.any { it in '0'..'9' }
    }

    companion object {
        private val REGEX_EMAIL = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    }
}