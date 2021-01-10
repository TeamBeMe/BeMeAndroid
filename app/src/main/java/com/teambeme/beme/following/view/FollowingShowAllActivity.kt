package com.teambeme.beme.following.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityFollowingShowAllBinding
import com.teambeme.beme.databinding.ItemFollowingShowAllProfilesBinding
import com.teambeme.beme.following.adapter.FollowingProfilesRcvAdapter
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.util.StatusBarUtil

class FollowingShowAllActivity :
    BindingActivity<ActivityFollowingShowAllBinding>(R.layout.activity_following_show_all) {
    private val followingShowAllViewModel: FollowingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        StatusBarUtil.setStatusBar(this, Color.WHITE)
        binding.followingShowAllViewModel = followingShowAllViewModel
        binding.lifecycleOwner = this
        followingShowAllViewModel.setDummyFollowingShowAllProfiles()
        Log.d("tag", followingShowAllViewModel.followingShowAllProfilesList.value.toString())
        setFollowingShowAllProfilesAdapter()
        setFollowingShowAllProfilesObserve()
        setClickListenerForGoback()
        setShowAllListFromTabLayoutAtFirst()
        setTabSelectedFromFollowingShowAllListener()
    }

    private fun setFollowingShowAllProfilesObserve() {
        followingShowAllViewModel.followingShowAllProfilesList.observe(this) { followingShowAllProfilesList ->
            followingShowAllProfilesList?.let {
                if (binding.rcvFollowingShowAllProfiles.adapter != null) with(binding.rcvFollowingShowAllProfiles.adapter as FollowingProfilesRcvAdapter<*>) {
                    submitList(followingShowAllProfilesList)
                }
            }
        }
    }

    private fun setFollowingShowAllProfilesAdapter() {
        val followingShowAllProfilesAdapter =
            FollowingProfilesRcvAdapter<ItemFollowingShowAllProfilesBinding>(
                this,
                R.layout.item_following_show_all_profiles
            )
        binding.rcvFollowingShowAllProfiles.adapter = followingShowAllProfilesAdapter
    }

    private fun setClickListenerForGoback() {
        binding.btnFollowingShowAllGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setShowAllListFromTabLayoutAtFirst() {
        if (binding.tabLayoutFollowingShowAllSort.selectedTabPosition == 0) {
            followingShowAllViewModel.selectFollowingShowAll(followingShowAllViewModel.dummyFollowingShowAllProfilesList)
        }
    }

    private fun setTabSelectedFromFollowingShowAllListener() {
        binding.tabLayoutFollowingShowAllSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            followingShowAllViewModel.selectFollowingShowAll(
                                followingShowAllViewModel.dummyFollowingShowAllProfilesList
                            )
                        }
                        1 -> {
                            followingShowAllViewModel.selectFollowerShowAll(
                                followingShowAllViewModel.dummyFollowingShowAllProfilesList
                            )
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
                TODO("Not yet implemented")
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
        })
    }
}