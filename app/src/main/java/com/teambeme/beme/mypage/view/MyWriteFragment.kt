package com.teambeme.beme.mypage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.databinding.FragmentMyWriteBinding
import com.teambeme.beme.mypage.adapter.MyWriteAdapter
import com.teambeme.beme.mypage.viewmodel.MyPageViewModel

class MyWriteFragment : Fragment() {
    private lateinit var binding: FragmentMyWriteBinding
    private val mypageViewModel: MyPageViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_write, container, false)
        val writeAdapter = MyWriteAdapter()
        binding.rcvMywrite.apply {
            adapter = writeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        mypageViewModel.setDummyWrite()

        mypageViewModel.mypageWriteData.observe(viewLifecycleOwner) { it ->
            it.let { writeAdapter.replaceWriteList(it) }
        }
        return binding.root
    }
}