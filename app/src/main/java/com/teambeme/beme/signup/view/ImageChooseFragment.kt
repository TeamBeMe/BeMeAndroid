package com.teambeme.beme.signup.view

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
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

        binding.imgChooseImagepick.setOnClickListener {
            Toast.makeText(requireContext(), "토스트", Toast.LENGTH_SHORT).show()
            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    pickImage()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {}
            }
            TedPermission.with(requireContext())
                .setPermissionListener(permissionListener)
                .setRationaleConfirmText("이미지 앨범을 접근하기 위해 접근 권한이 필요합니다")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용할 수 있습니다")
                .setGotoSettingButton(true)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()
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

    private fun pickImage() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        intent.putExtra("crop", "true")
//        startActivityForResult(
//            Intent.createChooser(intent, "Select Picture"),
//            BottomProfileFragment.PICK_IMAGE_REQUEST
//        )
    }
}