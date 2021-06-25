package com.teambeme.beme.mypage.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomProfileBinding
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@AndroidEntryPoint
class BottomProfileFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomProfileBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mypageViewModel = mypageViewModel
        binding.tbProfilebottomEdit.setOnClickListener {
            addPermission()
        }
        binding.tbProfilebottomBase.setOnClickListener {
            baseImage()
        }
        return binding.root
    }

    private fun baseImage() {
        val jsonObject = JSONObject()
        jsonObject.put("image", "")
        val fileReqBody = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("image", "", fileReqBody)
        mypageViewModel.putProfiles(part)
        mypageViewModel.setProfileUri(null)
        dismiss()
    }

    private fun addPermission() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() { imagePicker() }
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

    private fun imagePicker() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("crop", "true")
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            CropImage.activity(data.data!!).setAspectRatio(4, 3).start(requireContext(), this)
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                var resultUri = result.getUri()

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
                mypageViewModel.putProfiles(part)
                mypageViewModel.setProfileUri(resultUri)
                dismiss()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.PNG, 1, bytes)
        val titlename = Math.random()
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "asdsad", null)
        return Uri.parse(path)
    }

    companion object {
        private val PICK_IMAGE_REQUEST: Int = 2
    }
}
