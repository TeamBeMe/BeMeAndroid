package com.teambeme.beme.idsearchfollowing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityFollowingAfterIdSearchBinding
import com.teambeme.beme.idsearchfollowing.adapter.FollowAfterIdSearchAdapter
import com.teambeme.beme.idsearchfollowing.adapter.RecentSearchAdapter
import com.teambeme.beme.idsearchfollowing.viewmodel.FollowingAfterIdSearchViewModel

class FollowingAfterIdSearchActivity :
    BindingActivity<ActivityFollowingAfterIdSearchBinding>(R.layout.activity_following_after_id_search) {
    private val followingAfterIdSearchViewModel: FollowingAfterIdSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)

        initBinding(binding)
        setRecentSearchAdapter(binding)
        setIdSearchAdapter(binding)

        followingAfterIdSearchViewModel.setDummyRecentSearch()
        followingAfterIdSearchViewModel.setDummyIdSearchList()

        binding.searchViewFollowingIdsearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInputText = newText.let {
                    it
                } ?: ""
                if (userInputText.count() > 0) {
                    binding.viewRecentSearch.visibility = View.INVISIBLE
                    binding.rcvFollowingAfterIdsearch.visibility = View.VISIBLE
                } else {
                    binding.viewRecentSearch.visibility = View.VISIBLE
                    binding.rcvFollowingAfterIdsearch.visibility = View.INVISIBLE
                }
                return true
            }
        })

    }

    private fun initBinding(binding: ActivityFollowingAfterIdSearchBinding) {
        binding.apply {
            followingAfterIdSearchViewModel = followingAfterIdSearchViewModel
            lifecycleOwner = this@FollowingAfterIdSearchActivity
        }
    }

    private fun setRecentSearchAdapter(binding: ActivityFollowingAfterIdSearchBinding) {
        val recentSearchAdapter = RecentSearchAdapter()
        binding.rcvRecentSearch.apply {
            adapter = recentSearchAdapter
            layoutManager = LinearLayoutManager(this@FollowingAfterIdSearchActivity)
        }
        followingAfterIdSearchViewModel.recentSearchList.observe(this) { list ->
            recentSearchAdapter.replaceRecentSearchList(list)
        }
    }

    private fun setIdSearchAdapter(binding: ActivityFollowingAfterIdSearchBinding) {
        val followAfterIdSearchAdapter = FollowAfterIdSearchAdapter()
        binding.rcvFollowingAfterIdsearch.apply {
            adapter = followAfterIdSearchAdapter
            layoutManager = LinearLayoutManager(this@FollowingAfterIdSearchActivity)
        }
        followingAfterIdSearchViewModel.idSearchList.observe(this) { list ->
            followAfterIdSearchAdapter.replaceIdSearchList(list)
        }
    }
}