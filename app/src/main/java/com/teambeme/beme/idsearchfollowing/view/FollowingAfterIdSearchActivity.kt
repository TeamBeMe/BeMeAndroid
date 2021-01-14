package com.teambeme.beme.idsearchfollowing.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.IdSearchDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.ActivityFollowingAfterIdSearchBinding
import com.teambeme.beme.idsearchfollowing.adapter.RecentSearchAdapter
import com.teambeme.beme.idsearchfollowing.repository.IdSearchRepositoryImpl
import com.teambeme.beme.idsearchfollowing.viewmodel.IdSearchViewModel
import com.teambeme.beme.idsearchfollowing.viewmodel.IdSearchViewModelFactory

class FollowingAfterIdSearchActivity :
    BindingActivity<ActivityFollowingAfterIdSearchBinding>(R.layout.activity_following_after_id_search) {
    private val idSearchViewModelFactory =
        IdSearchViewModelFactory(IdSearchRepositoryImpl(IdSearchDataSourceImpl(RetrofitObjects.getIdSearchService())))

    private val idSearchViewModel: IdSearchViewModel by viewModels { idSearchViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        initBinding(binding)
        setRecentSearchAdapter(binding)
        idSearchViewModel.requestRecentSearchData()

        backBtnWorking()
    }

    private fun initBinding(binding: ActivityFollowingAfterIdSearchBinding) {
        binding.apply {
            idSearchViewModel = idSearchViewModel
            lifecycleOwner = this@FollowingAfterIdSearchActivity
        }
    }

    private fun setRecentSearchAdapter(binding: ActivityFollowingAfterIdSearchBinding) {
        val recentSearchAdapter = RecentSearchAdapter(idSearchViewModel)
        binding.rcvRecentSearch.apply {
            adapter = recentSearchAdapter
            layoutManager = LinearLayoutManager(this@FollowingAfterIdSearchActivity)
        }
        idSearchViewModel.recentSearchData.observe(this) { list ->
            recentSearchAdapter.replaceRecentSearchList(list)
        }
        idSearchViewModel.deletePosition.observe(this) {
            deleteListener()
        }
    }

    private fun deleteListener() {
        idSearchViewModel.deleteRecentSearch()
    }

    private fun backBtnWorking() {
        binding.btnBackFollowingIdsearch.setOnClickListener {
            onBackPressed()
        }
    }
}