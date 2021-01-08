package com.teambeme.beme.following.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.databinding.FragmentFollowingBinding
import com.teambeme.beme.databinding.ItemExploreOtherQuestionsBinding
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
        binding.followingViewModel = followingViewModel
        binding.lifecycleOwner = this
        followingViewModel.setDummyOtherFollowingQuestions()
        followingViewModel.setDummyFollowingProfiles()
        setOtherFollowingQuestionsAdapter(binding)
        setOtherFollowingQuestionsObserve(binding)
        setFollowingProfilesAdapter(binding)
        setFollowingProfilesObserve(binding)
        return binding.root
    }

    private fun setOtherFollowingQuestionsAdapter(binding: FragmentFollowingBinding) {
        val otherFollowingQuestionsAdapter =
            OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                requireContext(), R.layout.item_explore_other_questions
            )
        binding.rcvFollowingOtherQuestions.adapter = otherFollowingQuestionsAdapter
    }

    private fun setOtherFollowingQuestionsObserve(binding: FragmentFollowingBinding) {
        followingViewModel.otherFollowingQuestionsList.observe(viewLifecycleOwner) { otherFollowingQuestionsList ->
            otherFollowingQuestionsList?.let {
                if (binding.rcvFollowingOtherQuestions.adapter != null) with(binding.rcvFollowingOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherFollowingQuestionsList)
                }
            }
        }
    }

    private fun setFollowingProfilesAdapter(binding: FragmentFollowingBinding) {
        val followingProfilesRcvAdapter = FollowingProfilesRcvAdapter(requireContext())
        binding.rcvFollowingOtherProfiles.adapter = followingProfilesRcvAdapter
    }

    private fun setFollowingProfilesObserve(binding: FragmentFollowingBinding) {
        followingViewModel.followingProfilesList.observe(viewLifecycleOwner) { followingProfilesList ->
            followingProfilesList?.let {
                if (binding.rcvFollowingOtherProfiles.adapter != null) with(binding.rcvFollowingOtherProfiles.adapter as FollowingProfilesRcvAdapter) {
                    submitList(followingProfilesList)
                }
            }
        }
    }

//    private fun setTabSelectedFromFollowingListener(binding: FragmentFollowingBinding) {
//        binding.tabLayoutFollowingSort.addOnTabSelectedListener(object :
//            TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.position?.let {
//                    when (tab.position) {
//                        0 -> {
//                            binding.txtExploreDescOfRecent.visibility = View.VISIBLE
//                            binding.txtExploreDescOfExciting.visibility = View.INVISIBLE
//                        }
//                        1 -> {
//                            binding.txtExploreDescOfRecent.visibility = View.INVISIBLE
//                            binding.txtExploreDescOfExciting.visibility = View.VISIBLE
//                        }
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        }
//        )
//    }
}