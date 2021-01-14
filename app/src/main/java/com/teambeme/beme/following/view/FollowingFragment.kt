package com.teambeme.beme.following.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.data.remote.datasource.FollowingDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.*
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.following.adapter.FollowerProfilesRcvAdapter
import com.teambeme.beme.following.adapter.FollowingProfilesRcvAdapter
import com.teambeme.beme.following.repository.FollowingRepositoryImpl
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.following.viewmodel.FollowingViewModelFactory

class FollowingFragment : BindingFragment<FragmentFollowingBinding>(R.layout.fragment_following) {
    private val followingViewModelFactory = FollowingViewModelFactory(
        FollowingRepositoryImpl(
            FollowingDataSourceImpl(RetrofitObjects.getFollowingService())
        )
    )
    private val followingViewModel: FollowingViewModel by activityViewModels { followingViewModelFactory }

    override fun onResume() {
        super.onResume()
        followingViewModel.requestFollowingFollowerAnswers(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
        binding.followingViewModel = followingViewModel
        binding.lifecycleOwner = this
        followingViewModel.requestFollowingFollowerAnswers()
        followingViewModel.requestFollowerFollowingList()
        setOtherFollowingQuestionsAdapter()
        setOtherFollowingQuestionsObserve()
        setFollowingProfilesAdapter()
        setFollowerProfilesAdapter()
        setFollowingProfilesObserve()
        setFollowerProfilesObserve()
        setListFromTabLayoutAtFirst()
        setTabSelectedFromFollowingListener()
        setClickListenerForShowAll()
        return binding.root
    }

    private fun setOtherFollowingQuestionsAdapter() {
        val otherFollowingQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(), R.layout.item_explore_other_questions, followingViewModel
            )
        binding.rcvFollowingOtherQuestions.adapter = otherFollowingQuestionsAdapter
    }

    private fun setOtherFollowingQuestionsObserve() {
        followingViewModel.followingFollowerAnswersList.observe(viewLifecycleOwner) { otherFollowingQuestionsList ->
            otherFollowingQuestionsList?.let {
                if (binding.rcvFollowingOtherQuestions.adapter != null) with(binding.rcvFollowingOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherFollowingQuestionsList)
                }
            }
        }
    }

    private fun setFollowingProfilesAdapter() {
        val followingProfilesRcvAdapter =
            FollowingProfilesRcvAdapter<ItemFollowingProfilesOfFollowingBinding>(
                requireContext(),
                R.layout.item_following_profiles_of_following
            )
        binding.rcvFollowingProfilesOfFollowing.adapter = followingProfilesRcvAdapter
    }

    private fun setFollowerProfilesAdapter() {
        val followerProfilesRcvAdapter =
            FollowerProfilesRcvAdapter<ItemFollowingProfilesOfFollowerBinding>(
                requireContext(),
                R.layout.item_following_profiles_of_follower
            )
        binding.rcvFollowingProfilesOfFollower.adapter = followerProfilesRcvAdapter
    }

    private fun setFollowingProfilesObserve() {
        followingViewModel.followingList.observe(viewLifecycleOwner) { followingProfilesList ->
            followingProfilesList?.let {
                if (binding.rcvFollowingProfilesOfFollowing.adapter != null) with(binding.rcvFollowingProfilesOfFollowing.adapter as FollowingProfilesRcvAdapter<*>) {
                    submitList(followingProfilesList)
                }
            }
        }
    }

    private fun setFollowerProfilesObserve() {
        followingViewModel.followerList.observe(viewLifecycleOwner) { followerProfilesList ->
            followerProfilesList?.let {
                if (binding.rcvFollowingProfilesOfFollower.adapter != null) with(binding.rcvFollowingProfilesOfFollower.adapter as FollowerProfilesRcvAdapter<*>) {
                    submitList(followerProfilesList)
                }
            }
        }
    }

    private fun setListFromTabLayoutAtFirst() {
        if (binding.tabLayoutFollowingSort.selectedTabPosition == 0) {
        }
    }

    private fun setTabSelectedFromFollowingListener() {
        binding.tabLayoutFollowingSort.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    when (tab.position) {
                        0 -> {
                            binding.rcvFollowingProfilesOfFollowing.visibility = View.VISIBLE
                            binding.rcvFollowingProfilesOfFollower.visibility = View.INVISIBLE
                        }
                        1 -> {
                            binding.rcvFollowingProfilesOfFollowing.visibility = View.INVISIBLE
                            binding.rcvFollowingProfilesOfFollower.visibility = View.VISIBLE
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