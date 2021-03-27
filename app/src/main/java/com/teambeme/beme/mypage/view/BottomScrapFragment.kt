package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teambeme.beme.R
import com.teambeme.beme.data.remote.datasource.MyPageDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ItemBottomScrapBinding
import com.teambeme.beme.data.repository.MyPageRepositoryImpl
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel
import com.teambeme.beme.mypage.viewmodel.MyPageViewModelFactory

class BottomScrapFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomScrapBinding
    private val myViewModelFactory =
        MyPageViewModelFactory(MyPageRepositoryImpl(MyPageDataSourceImpl(RetrofitObjects.getMyPageService())))
    private val mypageViewModel: MyPageViewModel by activityViewModels { myViewModelFactory }
    private var category: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_bottom_scrap, container, false)
        binding.lifecycleOwner = this
        binding.myPageViewModel = mypageViewModel
        binding.txtScrapsheetApply.setOnClickListener {
            applyFilter()
            dismiss()
        }
        binding.chipGroupScrapCategori.setOnCheckedChangeListener { group, checkedId ->
            setCategoryOnCheckedListener(checkedId)
        }

        return binding.root
    }

    private fun setCategoryOnCheckedListener(checkId: Int) {
        when (checkId) {
            R.id.chip_scrap_1 -> category = CATEGORY_VALUE
            R.id.chip_scrap_2 -> category = CATEGORY_RELATION
            R.id.chip_scrap_3 -> category = CATEGORY_LOVE
            R.id.chip_scrap_4 -> category = CATEGORY_DAILY
            R.id.chip_scrap_5 -> category = CATEGORY_ME
            R.id.chip_scrap_6 -> category = CATEGORY_STORY
        }
    }

    private fun applyFilter() {
    }

    companion object {
        private val CATEGORY_VALUE = 1
        private val CATEGORY_RELATION = 2
        private val CATEGORY_LOVE = 3
        private val CATEGORY_DAILY = 4
        private val CATEGORY_ME = 5
        private val CATEGORY_STORY = 6
    }
}