package com.teambeme.beme.following.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.data.remote.datasource.FollowingDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.*
import com.teambeme.beme.following.adapter.FollowerProfilesRcvAdapter
import com.teambeme.beme.following.adapter.FollowingProfilesRcvAdapter
import com.teambeme.beme.following.adapter.SearchProfilesRcvAdapter
import com.teambeme.beme.following.repository.FollowingRepositoryImpl
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.following.viewmodel.FollowingViewModelFactory
import com.teambeme.beme.util.StatusBarUtil

class FollowingShowAllActivity :
    BindingActivity<ActivityFollowingShowAllBinding>(R.layout.activity_following_show_all) {
    private val followingViewModelFactory = FollowingViewModelFactory(
        FollowingRepositoryImpl(
            FollowingDataSourceImpl(RetrofitObjects.getFollowingService())
        )
    )
    private val followingShowAllViewModel: FollowingViewModel by viewModels { followingViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        binding.followingShowAllViewModel = followingShowAllViewModel
        binding.lifecycleOwner = this
        followingShowAllViewModel.requestFollowerFollowingList()
        setFollowingShowAllProfilesAdapter()
        setFollowerShowAllProfilesAdapter()
        setFollowingSearchProfilesAdapter()
        setFollowerSearchProfilesAdapter()
        setFollowingShowAllProfilesObserve()
        setFollowerShowAllProfilesObserve()
        setFollowingSearchProfilesObserve()
        setFollowerSearchProfilesObserve()
        setClickListenerForGoback()
        setShowAllListFromTabLayoutAtFirst()
        setTabSelectedFromFollowingShowAllListener()
        setQueryTextListener()
    }

    private fun setFollowingShowAllProfilesAdapter() {
        val followingShowAllProfilesAdapter =
            FollowingProfilesRcvAdapter<ItemFollowingShowAllProfilesOfFollowingBinding>(
                this,
                R.layout.item_following_show_all_profiles_of_following,
                followingShowAllViewModel
            )
        binding.rcvFollowingShowAllProfilesFollowing.adapter = followingShowAllProfilesAdapter
    }

    private fun setFollowerShowAllProfilesAdapter() {
        val followerShowAllProfilesAdapter =
            FollowerProfilesRcvAdapter<ItemFollowingShowAllProfilesOfFollowerBinding>(
                this,
                R.layout.item_following_show_all_profiles_of_follower
            )
        binding.rcvFollowingShowAllProfilesFollower.adapter = followerShowAllProfilesAdapter
    }

    private fun setFollowingSearchProfilesAdapter() {
        val followingSearchProfilesAdapter =
            SearchProfilesRcvAdapter<ItemFollowingShowAllProfilesOfSearchFollowingBinding>(
                this,
                R.layout.item_following_show_all_profiles_of_search_following,
                followingShowAllViewModel
            )
        binding.rcvFollowingShowAllProfilesSearchFollowing.adapter = followingSearchProfilesAdapter
    }

    private fun setFollowerSearchProfilesAdapter() {
        val followerSearchProfilesAdapter =
            SearchProfilesRcvAdapter<ItemFollowingShowAllProfilesOfSearchFollowerBinding>(
                this,
                R.layout.item_following_show_all_profiles_of_search_follower,
                followingShowAllViewModel
            )
        binding.rcvFollowingShowAllProfilesSearchFollower.adapter = followerSearchProfilesAdapter
    }

    private fun setFollowingShowAllProfilesObserve() {
        followingShowAllViewModel.followingList.observe(this) { followingShowAllProfilesList ->
            followingShowAllProfilesList?.let {
                if (binding.rcvFollowingShowAllProfilesFollowing.adapter != null) with(binding.rcvFollowingShowAllProfilesFollowing.adapter as FollowingProfilesRcvAdapter<*>) {
                    submitList(followingShowAllProfilesList)
                }
                if (followingShowAllProfilesList.size == 0) {
                    binding.rcvFollowingShowAllProfilesFollowing.visibility = View.INVISIBLE
                    binding.searchViewFollowingShowAll.visibility = View.INVISIBLE
                    binding.txtFollowingShowAllFollowerEmptyInformation.visibility = View.INVISIBLE
                    binding.txtFollowingShowAllFollowingEmptyInformation.visibility = View.VISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.VISIBLE
                } else {
                    if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 0) {
                        binding.rcvFollowingShowAllProfilesFollowing.visibility = View.VISIBLE
                    }
                    binding.txtFollowingShowAllFollowingEmptyInformation.visibility = View.INVISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setFollowerShowAllProfilesObserve() {
        followingShowAllViewModel.followerList.observe(this) { followerShowAllProfilesList ->
            followerShowAllProfilesList?.let {
                if (binding.rcvFollowingShowAllProfilesFollower.adapter != null) with(binding.rcvFollowingShowAllProfilesFollower.adapter as FollowerProfilesRcvAdapter<*>) {
                    submitList(followerShowAllProfilesList)
                }
                if (followerShowAllProfilesList.size == 0) {
                    binding.rcvFollowingShowAllProfilesFollower.visibility = View.INVISIBLE
                    binding.searchViewFollowingShowAll.visibility = View.INVISIBLE
                    binding.txtFollowingShowAllFollowingEmptyInformation.visibility = View.INVISIBLE
                    binding.txtFollowingShowAllFollowerEmptyInformation.visibility = View.VISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.VISIBLE
                } else {
                    if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 1) {
                        binding.rcvFollowingShowAllProfilesFollower.visibility = View.VISIBLE
                    }
                    binding.txtFollowingShowAllFollowerEmptyInformation.visibility = View.INVISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setFollowingSearchProfilesObserve() {
        followingShowAllViewModel.searchList.observe(this) { followingSearchList ->
            followingSearchList?.let {
                if (binding.rcvFollowingShowAllProfilesSearchFollowing.adapter != null) with(binding.rcvFollowingShowAllProfilesSearchFollowing.adapter as SearchProfilesRcvAdapter<*>) {
                    submitList(followingSearchList)
                }
                if (followingSearchList[0].isFollowed == null) {
                    binding.txtFollowingShowAllNoSearchInformation.visibility = View.VISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.VISIBLE
                } else {
                    binding.txtFollowingShowAllNoSearchInformation.visibility = View.INVISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setFollowerSearchProfilesObserve() {
        followingShowAllViewModel.searchList.observe(this) { followerSearchList ->
            followerSearchList?.let {
                if (binding.rcvFollowingShowAllProfilesSearchFollower.adapter != null) with(binding.rcvFollowingShowAllProfilesSearchFollower.adapter as SearchProfilesRcvAdapter<*>) {
                    submitList(followerSearchList)
                }
                if (followerSearchList[0].isFollowed == null) {
                    binding.txtFollowingShowAllNoSearchInformation.visibility = View.VISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.VISIBLE
                } else {
                    binding.txtFollowingShowAllNoSearchInformation.visibility = View.INVISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setClickListenerForGoback() {
        binding.btnFollowingShowAllGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setShowAllListFromTabLayoutAtFirst() {
        if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 0) {
            //
        }
    }

    private fun setTabSelectedFromFollowingShowAllListener() {
        binding.tabLayoutFollowingShowAllSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            followingShowAllViewModel.setSearchRange("followee")
                            binding.searchViewFollowingShowAll.setQuery(null, false)
                            binding.rcvFollowingShowAllProfilesFollower.visibility = View.INVISIBLE
                            if (followingShowAllViewModel.followingList.value?.size == 0) {
                                binding.rcvFollowingShowAllProfilesFollowing.visibility =
                                    View.INVISIBLE
                                binding.txtFollowingShowAllFollowerEmptyInformation.visibility =
                                    View.INVISIBLE
                                binding.txtFollowingShowAllFollowingEmptyInformation.visibility =
                                    View.VISIBLE
                                binding.imgFollowingShowAllEmptyInformation.visibility =
                                    View.VISIBLE
                            } else {
                                binding.rcvFollowingShowAllProfilesFollowing.visibility =
                                    View.VISIBLE
                            }
                        }
                        1 -> {
                            followingShowAllViewModel.setSearchRange("follower")
                            binding.searchViewFollowingShowAll.setQuery(null, false)
                            binding.rcvFollowingShowAllProfilesFollowing.visibility = View.INVISIBLE
                            if (followingShowAllViewModel.followerList.value?.size == 0) {
                                binding.rcvFollowingShowAllProfilesFollower.visibility =
                                    View.INVISIBLE
                                binding.txtFollowingShowAllFollowingEmptyInformation.visibility =
                                    View.INVISIBLE
                                binding.txtFollowingShowAllFollowerEmptyInformation.visibility =
                                    View.VISIBLE
                                binding.imgFollowingShowAllEmptyInformation.visibility =
                                    View.VISIBLE
                            } else {
                                binding.rcvFollowingShowAllProfilesFollower.visibility =
                                    View.VISIBLE
                            }
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
        )
    }

    private fun setQueryTextListener() {
        binding.searchViewFollowingShowAll.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                val queryText = newText ?: ""
                if (queryText.count() > 0) {
                    binding.rcvFollowingShowAllProfilesFollower.visibility = View.INVISIBLE
                    binding.rcvFollowingShowAllProfilesFollowing.visibility = View.INVISIBLE
                    if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 0) {
                        binding.rcvFollowingShowAllProfilesSearchFollowing.visibility = View.VISIBLE
                    } else {
                        binding.rcvFollowingShowAllProfilesSearchFollower.visibility = View.VISIBLE
                    }
                } else {
                    followingShowAllViewModel.deleteSearchRecord()
                    followingShowAllViewModel.requestFollowerFollowingList()
                    binding.txtFollowingShowAllNoSearchInformation.visibility = View.INVISIBLE
                    binding.imgFollowingShowAllEmptyInformation.visibility = View.INVISIBLE
                    if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 0) {
                        binding.rcvFollowingShowAllProfilesFollowing.visibility = View.VISIBLE
                    } else {
                        binding.rcvFollowingShowAllProfilesFollower.visibility = View.VISIBLE
                    }
                    binding.rcvFollowingShowAllProfilesSearchFollower.visibility = View.INVISIBLE
                    binding.rcvFollowingShowAllProfilesSearchFollowing.visibility = View.INVISIBLE
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("text", query.toString())
                if (query != null) {
                    followingShowAllViewModel.setSearchQuery(query)
                    followingShowAllViewModel.requestSearchMyFollowingFollower()
                }
                return false
            }
        })
    }
}