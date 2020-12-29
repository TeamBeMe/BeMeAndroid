package com.teambeme.beme.explore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.explore.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }
}