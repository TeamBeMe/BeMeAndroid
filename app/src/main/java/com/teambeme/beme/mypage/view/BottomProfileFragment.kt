package com.teambeme.beme.mypage.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.databinding.ItemBottomProfileBinding
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.theartofdev.edmodo.cropper.CropImage

class BottomProfileFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomProfileBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    private val PICK_IMAGE_REQUEST: Int = 2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_profile, container, false)
        binding.lifecycleOwner = this
        binding.mypageViewModel = mypageViewModel
        binding.tbProfilebottomEdit.setOnClickListener {
            ImagePicker()
        }
        return binding.root
    }

    private fun ImagePicker() {
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
                mypageViewModel.setProfileUri(resultUri)
                dismiss()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.getError()
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}