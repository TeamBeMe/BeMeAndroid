package com.teambeme.beme.signup.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentImageChooseBinding
import com.teambeme.beme.signup.viewmodel.SignUpViewModel
import com.teambeme.beme.util.recordClickEvent
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

@AndroidEntryPoint
class ImageChooseFragment :
    BindingFragment<FragmentImageChooseBinding>(R.layout.fragment_image_choose) {
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "SignUpActivity")
            param(FirebaseAnalytics.Param.SCREEN_NAME, "PROFILE_SIGN")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUiClickListener()
        subscribeData()
        return binding.root
    }

    private fun subscribeData() {
        signUpViewModel.profileImageUri.observe(viewLifecycleOwner) {
            binding.imgChooseImagepick.visibility = View.GONE
            binding.imgChooseImage.apply {
                isVisible = true
                setImageURI(it)
            }
        }
        signUpViewModel.signUpUserInfo.observe(viewLifecycleOwner) { userInfo ->
            if (userInfo != null) {
                when (userInfo.success) {
                    true -> {
                        Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                        requireActivity().finish()
                    }
                    else -> {
                        Log.d("SignUp", userInfo.message)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "회원가입이 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUiClickListener() {
        binding.btnBack.setOnClickListener { view ->
            recordClickEvent("BACK_PRESS", "OUT_PROFILE_SIGN")
            view.findNavController().popBackStack()
        }

        binding.btnImageChooseDone.setOnClickListener {
            lifecycleScope.launch {
                signUpViewModel.signUp().join()
                Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imgChooseImagepick.setOnClickListener {
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
                .setPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .check()
        }
    }

    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("crop", "true")
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            CropImage.activity(data.data!!).setAspectRatio(4, 3).start(requireContext(), this)
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                signUpViewModel.setProfileUri(resultUri)
                val options = BitmapFactory.Options()
                val inputStream = requireContext().contentResolver.openInputStream(resultUri)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                val photoBody =
                    RequestBody.create(
                        "image/jpg".toMediaTypeOrNull(),
                        byteArrayOutputStream.toByteArray()
                    )
                val part = MultipartBody.Part.createFormData(
                    "image",
                    File(resultUri.toString()).name,
                    photoBody
                )
                signUpViewModel.setProfilePart(part)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val PICK_IMAGE_REQUEST = 1004
    }
}
