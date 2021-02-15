package com.teambeme.beme.following.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.teambeme.beme.R
import com.teambeme.beme.answer.model.IntentAnswerData
import com.teambeme.beme.answer.view.AnswerActivity
import com.teambeme.beme.base.BindingFragment
import com.teambeme.beme.data.remote.datasource.FollowingDataSourceImpl
import com.teambeme.beme.data.remote.singleton.RetrofitObjects
import com.teambeme.beme.databinding.*
import com.teambeme.beme.explore.adapter.OtherQuestionsRcvAdapter
import com.teambeme.beme.following.adapter.FollowerProfilesRcvAdapter
import com.teambeme.beme.following.adapter.FollowingProfilesRcvAdapter
import com.teambeme.beme.following.model.RequestFollowingAnswer
import com.teambeme.beme.following.repository.FollowingRepositoryImpl
import com.teambeme.beme.following.viewmodel.FollowingViewModel
import com.teambeme.beme.following.viewmodel.FollowingViewModelFactory
import com.teambeme.beme.idsearchfollowing.view.FollowingAfterIdSearchActivity
import com.teambeme.beme.notification.view.NotificationActivity

class FollowingFragment : BindingFragment<FragmentFollowingBinding>(R.layout.fragment_following) {
    private val followingViewModelFactory = FollowingViewModelFactory(
        FollowingRepositoryImpl(
            FollowingDataSourceImpl(RetrofitObjects.getFollowingService())
        )
    )
    private val followingViewModel: FollowingViewModel by activityViewModels { followingViewModelFactory }

    override fun onResume() {
        super.onResume()
        followingViewModel.requestFollowingFollowerAnswers(followingViewModel.tempPage)
        followingViewModel.requestFollowerFollowingList()
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
        setClickListenerForIdSearchButton()
        setClickListenerForAlarmButton()
        setDoAnswerDataObserve()
        setIsMorePageObserve()
        setListenerForPullRefreshLayout()
        return binding.root
    }

    private fun setIsMorePageObserve() {
        followingViewModel.isMorePage.observe(viewLifecycleOwner) { morePage ->
            morePage?.let {
                if (morePage == true) {
                    binding.btnFollowingShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnFollowingShowMore.visibility = View.GONE
                }
            }
        }
    }

    private fun setDoAnswerDataObserve() {
        followingViewModel.answerData.observe(viewLifecycleOwner) {
            it?.let {
                val intent = Intent(context, AnswerActivity::class.java)
                Log.d("answer", "fragment " + "${followingViewModel.answerData.value}")
                var intentAnswerData: IntentAnswerData = IntentAnswerData(
                    it.questionId,
                    it.id,
                    it.question,
                    it.category,
                    it.answerIdx,
                    it.createdAt
                )
                intent.putExtra("intentAnswerData", intentAnswerData)
                startActivity(intent)
            }
        }
    }

    private fun getOtherButtonClickListener(): OtherQuestionsRcvAdapter.OtherQuestionButtonClickListener {
        return object : OtherQuestionsRcvAdapter.OtherQuestionButtonClickListener {
            override fun otherQuestionAnswerClickListener(questionId: Int) {
                val answer = RequestFollowingAnswer(questionId)
                followingViewModel.requestAnswer(answer)
            }
        }
    }

    private fun setOtherFollowingQuestionsAdapter() {
        followingViewModel.userNickname.observe(viewLifecycleOwner) {
            it?.let {
                val otherFollowingQuestionsAdapter =
                    OtherQuestionsRcvAdapter<ItemExploreOtherQuestionsBinding>(
                        requireContext(),
                        R.layout.item_explore_other_questions,
                        it,
                        followingViewModel,
                        getOtherButtonClickListener()
                    )
                binding.rcvFollowingOtherQuestions.adapter = otherFollowingQuestionsAdapter
            }
        }
    }

    private fun setOtherFollowingQuestionsObserve() {
        followingViewModel.followingAnswersList.observe(viewLifecycleOwner) { otherFollowingQuestionsList ->
            otherFollowingQuestionsList?.let {
                if (binding.rcvFollowingOtherQuestions.adapter != null) with(binding.rcvFollowingOtherQuestions.adapter as OtherQuestionsRcvAdapter<*>) {
                    submitList(otherFollowingQuestionsList)
                }
                if (otherFollowingQuestionsList.size == 0) {
                    binding.rcvFollowingOtherQuestions.visibility = View.GONE
                    binding.imgFollowingNoFollowingAnswerInformation.visibility = View.VISIBLE
                    binding.txtFollowingNoFollowingAnswerInformation.visibility = View.VISIBLE
                } else {
                    binding.rcvFollowingOtherQuestions.visibility = View.VISIBLE
                    binding.imgFollowingNoFollowingAnswerInformation.visibility = View.GONE
                    binding.txtFollowingNoFollowingAnswerInformation.visibility = View.GONE
                }
            }
        }
    }

    private fun setFollowingProfilesAdapter() {
        val followingProfilesRcvAdapter =
            FollowingProfilesRcvAdapter<ItemFollowingProfilesOfFollowingBinding>(
                requireContext(),
                R.layout.item_following_profiles_of_following,
                followingViewModel
            )
        binding.rcvFollowingProfilesOfFollowing.adapter = followingProfilesRcvAdapter
    }

    private fun setFollowerProfilesAdapter() {
        val followerProfilesRcvAdapter =
            FollowerProfilesRcvAdapter<ItemFollowingProfilesOfFollowerBinding>(
                requireContext(),
                R.layout.item_following_profiles_of_follower,
                followingViewModel
            )
        binding.rcvFollowingProfilesOfFollower.adapter = followerProfilesRcvAdapter
    }

    private fun setFollowingProfilesObserve() {
        followingViewModel.followingList.observe(viewLifecycleOwner) { followingProfilesList ->
            followingProfilesList?.let {
                if (binding.rcvFollowingProfilesOfFollowing.adapter != null) with(binding.rcvFollowingProfilesOfFollowing.adapter as FollowingProfilesRcvAdapter<*>) {
                    submitList(followingProfilesList)
                }
                if (binding.tabLayoutFollowingSort.selectedTabPosition == 0) {
                    if (followingProfilesList.size == 0) {
                        binding.rcvFollowingProfilesOfFollowing.visibility = View.INVISIBLE
                        binding.txtFollowingNoFollowerListInformation.visibility = View.INVISIBLE
                        binding.imgFollowingNoFollowingListInformation.visibility = View.VISIBLE
                        binding.txtFollowingNoFollowingListInformation.visibility = View.VISIBLE
                    } else {
                        binding.rcvFollowingProfilesOfFollowing.visibility = View.VISIBLE
                        binding.imgFollowingNoFollowingListInformation.visibility = View.INVISIBLE
                        binding.txtFollowingNoFollowingListInformation.visibility = View.INVISIBLE
                    }
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
                if (binding.tabLayoutFollowingSort.selectedTabPosition == 1) {
                    if (followerProfilesList.size == 0) {
                        binding.rcvFollowingProfilesOfFollower.visibility = View.INVISIBLE
                        binding.txtFollowingNoFollowingListInformation.visibility = View.INVISIBLE
                        binding.imgFollowingNoFollowingListInformation.visibility = View.VISIBLE
                        binding.txtFollowingNoFollowerListInformation.visibility = View.VISIBLE
                    } else {
                        binding.rcvFollowingProfilesOfFollower.visibility = View.VISIBLE
                        binding.imgFollowingNoFollowingListInformation.visibility = View.INVISIBLE
                        binding.txtFollowingNoFollowerListInformation.visibility = View.INVISIBLE
                    }
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
                            binding.rcvFollowingProfilesOfFollower.visibility = View.INVISIBLE
                            binding.txtFollowingNoFollowerListInformation.visibility =
                                View.INVISIBLE
                            if (followingViewModel.followingList.value?.size == 0) {
                                binding.rcvFollowingProfilesOfFollowing.visibility = View.INVISIBLE
                                binding.txtFollowingNoFollowerListInformation.visibility =
                                    View.INVISIBLE
                                binding.imgFollowingNoFollowingListInformation.visibility =
                                    View.VISIBLE
                                binding.txtFollowingNoFollowingListInformation.visibility =
                                    View.VISIBLE
                            } else {
                                binding.rcvFollowingProfilesOfFollowing.visibility = View.VISIBLE
                                binding.imgFollowingNoFollowingListInformation.visibility =
                                    View.INVISIBLE
                            }
                        }
                        1 -> {
                            binding.rcvFollowingProfilesOfFollowing.visibility = View.INVISIBLE
                            binding.txtFollowingNoFollowingListInformation.visibility =
                                View.INVISIBLE
                            if (followingViewModel.followerList.value?.size == 0) {
                                binding.rcvFollowingProfilesOfFollower.visibility = View.INVISIBLE
                                binding.txtFollowingNoFollowingListInformation.visibility =
                                    View.INVISIBLE
                                binding.imgFollowingNoFollowingListInformation.visibility =
                                    View.VISIBLE
                                binding.txtFollowingNoFollowerListInformation.visibility =
                                    View.VISIBLE
                            } else {
                                binding.rcvFollowingProfilesOfFollower.visibility = View.VISIBLE
                                binding.imgFollowingNoFollowingListInformation.visibility =
                                    View.INVISIBLE
                                binding.txtFollowingNoFollowerListInformation.visibility =
                                    View.INVISIBLE
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

    private fun setClickListenerForShowAll() {
        binding.txtFollowingShowAll.setOnClickListener {
            val intent = Intent(activity, FollowingShowAllActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setClickListenerForIdSearchButton() {
        binding.btnFollowingIdSearch.setOnClickListener {
            val intent = Intent(activity, FollowingAfterIdSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setClickListenerForAlarmButton() {
        binding.btnFollowingAlarm.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListenerForPullRefreshLayout() {
        binding.pullRefreshLayoutFollowing.setOnRefreshListener {
            followingViewModel.requestFollowingFollowerAnswers(1)
            followingViewModel.requestFollowerFollowingList()
            binding.pullRefreshLayoutFollowing.setRefreshing(false)
        }
    }
}