package com.teambeme.beme.signup.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

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
            CoroutineScope(Dispatchers.Main).launch {
                signUpViewModel.signUp().join()
                Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }

        signUpViewModel.profileImageUri.observe(viewLifecycleOwner) {
            binding.imgChooseImagepick.visibility = View.GONE
            binding.imgChooseImage.apply {
                visibility = View.VISIBLE
                setImageURI(it)
            }
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
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, resultUri)
                val bitmapToUri = getImageUri(requireContext(), bitmap)
                val filePathColumn =
                    arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor? =
                    requireContext().contentResolver.query(
                        bitmapToUri!!,
                        filePathColumn,
                        null,
                        null,
                        null
                    )
                cursor!!.moveToFirst()
                val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                val imgDecodableString = cursor.getString(columnIndex)
                signUpViewModel.setProfileString(imgDecodableString)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.PNG, 1, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "asdsad", null)
        return Uri.parse(path)
    }

    companion object {
        const val PICK_IMAGE_REQUEST = 1004
    }
}