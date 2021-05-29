package com.teambeme.beme.idsearchfollowing.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityFollowingAfterIdSearchBinding
import com.teambeme.beme.idsearchfollowing.adapter.IdSearchAdapter
import com.teambeme.beme.idsearchfollowing.adapter.RecentSearchAdapter
import com.teambeme.beme.idsearchfollowing.viewmodel.IdSearchViewModel
import com.teambeme.beme.otherpage.view.OtherPageActivity
import com.teambeme.beme.util.StatusBarUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class FollowingAfterIdSearchActivity :
    BindingActivity<ActivityFollowingAfterIdSearchBinding>(R.layout.activity_following_after_id_search) {
    private val idSearchViewModel: IdSearchViewModel by viewModels()
    private val recentSearchAdapter = RecentSearchAdapter(provideProfileButtonClickListener())
    private val idSearchAdapter = IdSearchAdapter(provideFollowButtonClickListener())

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        binding.idSearchViewModel = idSearchViewModel
        binding.lifecycleOwner = this
        initView()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun initView() {
        setAdapter()
        setViewListener()
        idSearchViewModel.requestRecentSearchData()
        subscribeData()
    }

    private fun setAdapter() {
        binding.rcvRecentSearch.apply {
            adapter = recentSearchAdapter
            layoutManager = LinearLayoutManager(this@FollowingAfterIdSearchActivity)
        }
        binding.rcvFollowingAfterIdsearch.adapter = idSearchAdapter
    }

    @ExperimentalCoroutinesApi
    private fun setViewListener() {
        binding.searchViewFollowingIdsearch.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    val queryText = newText ?: ""
                    idSearchViewModel.query = queryText
                    if (queryText.count() > 0) {
                        binding.viewRecentSearch.visibility = View.GONE
//                    binding.constraintViewFollowingAfterIdsearch.visibility = View.VISIBLE
                        idSearchViewModel.searchQuery.offer(queryText)
                    } else {
                        idSearchViewModel.deleteSearchRecord()
//                    idSearchViewModel.searchQuery.offer("")
                        binding.viewRecentSearch.visibility = View.VISIBLE
                        binding.constraintViewFollowingAfterIdsearch.visibility = View.GONE
                        binding.noticeWhenNoSearchData.visibility = View.GONE
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
//                    if (query != null) {
//                        idSearchViewModel.setSearchQuery(query)
//                        idSearchViewModel.requestIdSearchData()
//                    }
                    return true
                }
            })
        binding.btnBackFollowingIdsearch.setOnClickListener { onBackPressed() }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun subscribeData() {
        with(idSearchViewModel) {
            recentSearchData.observe(this@FollowingAfterIdSearchActivity) { list ->
                recentSearchAdapter.replaceList(list)
            }
            deletePosition.observe(this@FollowingAfterIdSearchActivity) {
                idSearchViewModel.deleteRecentSearch()
            }
//            idSearchData.observe(this@FollowingAfterIdSearchActivity) { idSearchData ->
//                idSearchData?.let {
//                    if (binding.rcvFollowingAfterIdsearch.adapter != null) {
//                        with(binding.rcvFollowingAfterIdsearch.adapter as IdSearchAdapter) {
//                            submitList(idSearchData)
//                        }
//                    }
//                    if (idSearchData[0].isFollowed == null) {
//                        binding.noticeWhenNoSearchData.visibility = View.VISIBLE
//                        binding.constraintViewFollowingAfterIdsearch.visibility = View.INVISIBLE
//                    } else {
//                        binding.noticeWhenNoSearchData.visibility = View.INVISIBLE
//                        binding.constraintViewFollowingAfterIdsearch.visibility = View.VISIBLE
//                    }
//                }
//            }
            errorMessage.observe(this@FollowingAfterIdSearchActivity) {
                Toast.makeText(this@FollowingAfterIdSearchActivity, it, Toast.LENGTH_SHORT)
                    .show()
            }
            searchResult.observe(this@FollowingAfterIdSearchActivity) { idSearchData ->
                idSearchData?.let {
                    if (idSearchViewModel.query.isNotEmpty()) {
                        if (it[0] == null) {
                            binding.noticeWhenNoSearchData.visibility = View.VISIBLE
                            binding.constraintViewFollowingAfterIdsearch.visibility = View.INVISIBLE
                        } else {
                            if (it[0]?.isFollowed == null) {
                                binding.noticeWhenNoSearchData.visibility = View.VISIBLE
                                binding.constraintViewFollowingAfterIdsearch.visibility =
                                    View.INVISIBLE
                            } else {
                                binding.noticeWhenNoSearchData.visibility = View.INVISIBLE
                                binding.constraintViewFollowingAfterIdsearch.visibility =
                                    View.VISIBLE
                            }
                            if (binding.rcvFollowingAfterIdsearch.adapter != null) {
                                idSearchAdapter.submitList(idSearchData)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun provideProfileButtonClickListener() = object : RecentSearchAdapter.ProfileButton {
        override fun setOnPicClickListener(id: Int) {
            val intent = Intent(this@FollowingAfterIdSearchActivity, OtherPageActivity::class.java)
            intent.putExtra("userId", id)
            startActivity(intent)
        }

        override fun setDeleteClickListener(position: Int) {
            idSearchViewModel.setPosition(position)
        }
    }

    private fun provideFollowButtonClickListener() = object : IdSearchAdapter.FollowButton {
        override fun setOnUnfollowClickListener(id: Int) {
            idSearchViewModel.requestFollowAndFollowing(id)
        }

        override fun setOnFollowClickListener(id: Int) {
            idSearchViewModel.requestFollowAndFollowing(id)
        }

        override fun setProfilePicClickListener(id: Int) {
            val intent = Intent(this@FollowingAfterIdSearchActivity, OtherPageActivity::class.java)
            intent.putExtra("userId", id)
            startActivity(intent)
        }
    }
}
