package com.teambeme.beme.following.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentFollowingBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
import com.teambeme.beme.databinding.ItemFollowingOtherProfilesBinding
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.following.adapter.FollowingProfilesRcvAdapter
import com.teambeme.beme.following.viewmodel.FollowingViewModel

class FollowingFragment : BindingFragment<FragmentFollowingBinding>(R.layout.fragment_following) {
    private val followingViewModel: FollowingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
        binding.followingViewModel = followingViewModel
        binding.lifecycleOwner = this
        followingViewModel.setDummyFollowingProfiles()
        setOtherFollowingQuestionsAdapter()
        setOtherFollowingQuestionsObserve()
        setFollowingProfilesAdapter()
        setFollowingProfilesObserve()
        setListFromTabLayoutAtFirst()
        setTabSelectedFromFollowingListener()
        setClickListenerForShowAll()
        return binding.root
    }

    private fun setOtherFollowingQuestionsAdapter() {
        val otherFollowingQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(), R.layout.item_explore_other_questions, followingViewModel)
        binding.rcvFollowingOtherQuestions.adapter = otherFollowingQuestionsAdapter
    }

    private fun setOtherFollowingQuestionsObserve() {
        followingViewModel.otherFollowingQuestionsList.observe(viewLifecycleOwner) { otherFollowingQuestionsList ->
            otherFollowingQuestionsList?.let {
                if (binding.rcvFollowingOtherQuestions.adapter != null) with(binding.rcvFollowingOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherFollowingQuestionsList)
                }
            }
        }
    }

    private fun setFollowingProfilesAdapter() {
        val followingProfilesRcvAdapter =
            FollowingProfilesRcvAdapter<ItemFollowingOtherProfilesBinding>(
                requireContext(),
                R.layout.item_following_other_profiles
            )
        binding.rcvFollowingOtherProfiles.adapter = followingProfilesRcvAdapter
    }

    private fun setFollowingProfilesObserve() {
        followingViewModel.followingProfilesList.observe(viewLifecycleOwner) { followingProfilesList ->
            followingProfilesList?.let {
                if (binding.rcvFollowingOtherProfiles.adapter != null) with(binding.rcvFollowingOtherProfiles.adapter as FollowingProfilesRcvAdapter<*>) {
                    submitList(followingProfilesList)
                }
            }
        }
    }

    private fun setListFromTabLayoutAtFirst() {
        if (binding.tabLayoutFollowingSort.selectedTabPosition == 0) {
            followingViewModel.selectFollowing(followingViewModel.dummyFollowingProfilesList)
        }
    }

    private fun setTabSelectedFromFollowingListener() {
        binding.tabLayoutFollowingSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            followingViewModel.selectFollowing(followingViewModel.dummyFollowingProfilesList)
                        }
                        1 -> {
                            followingViewModel.selectFollower(followingViewModel.dummyFollowingProfilesList)
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
        )
    }

    private fun setClickListenerForShowAll() {
        binding.txtFollowingShowAll.setOnClickListener {
            val intent = Intent(activity, FollowingShowAllActivity::class.java)
            startActivity(intent)
        }
    }
}