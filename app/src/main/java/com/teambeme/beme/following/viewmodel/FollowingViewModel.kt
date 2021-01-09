package com.teambeme.beme.following.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.R
import com.teambeme.beme.explore.model.OtherQuestionsData
import com.teambeme.beme.following.model.FollowingProfilesData

class FollowingViewModel : ViewModel() {
    private val _otherFollowingQuestionsList = MutableLiveData<MutableList<OtherQuestionsData>>()
    val otherFollowingQuestionsList: LiveData<MutableList<OtherQuestionsData>>
        get() = _otherFollowingQuestionsList

    private val _followingProfilesList = MutableLiveData<List<FollowingProfilesData>>()
    val followingProfilesList: LiveData<List<FollowingProfilesData>>
        get() = _followingProfilesList

    private val _followingShowAllProfilesList = MutableLiveData<List<FollowingProfilesData>>()
    val followingShowAllProfilesList: LiveData<List<FollowingProfilesData>>
        get() = _followingShowAllProfilesList

    private val _dummyFollowingShowAllProfilesList = listOf(
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "1_ox",
            isFollowing = true,
            isFollower = false
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "2_xo",
            isFollowing = false,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "3_oo",
            isFollowing = true,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "4_ox",
            isFollowing = true,
            isFollower = false
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "5_xo",
            isFollowing = false,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "6_oo",
            isFollowing = true,
            isFollower = true
        )
    )
    val dummyFollowingShowAllProfilesList: List<FollowingProfilesData>
        get() = _dummyFollowingShowAllProfilesList

    fun setDummyFollowingShowAllProfiles() {
        _followingShowAllProfilesList.value = dummyFollowingShowAllProfilesList.toList()
    }

    fun selectFollowingShowAll(followingProfilesList: List<FollowingProfilesData>) {
        val isFollowingList = followingProfilesList.filter { data -> data.isFollowing }
        _followingShowAllProfilesList.value = isFollowingList.toList()
    }

    fun selectFollowerShowAll(followingProfilesList: List<FollowingProfilesData>) {
        val isFollowerList = followingProfilesList.filter { data -> data.isFollower }
        _followingShowAllProfilesList.value = isFollowerList.toList()
    }

    private val _dummyFollowingProfilesList = listOf(
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "1_ox",
            isFollowing = true,
            isFollower = false
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "2_xo",
            isFollowing = false,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "3_oo",
            isFollowing = true,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "4_ox",
            isFollowing = true,
            isFollower = false
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "5_xo",
            isFollowing = false,
            isFollower = true
        ),
        FollowingProfilesData(
            profile_img = R.drawable.img_profile_sample_following,
            profile_id = "6_oo",
            isFollowing = true,
            isFollower = true
        )
    )
    val dummyFollowingProfilesList: List<FollowingProfilesData>
        get() = _dummyFollowingProfilesList

    fun setDummyFollowingProfiles() {
        _followingProfilesList.value = dummyFollowingProfilesList.toList()
    }

    fun selectFollowing(followingProfilesList: List<FollowingProfilesData>) {
        val isFollowingList = followingProfilesList.filter { data -> data.isFollowing }
        _followingProfilesList.value = isFollowingList.toList()
    }

    fun selectFollower(followingProfilesList: List<FollowingProfilesData>) {
        val isFollowerList = followingProfilesList.filter { data -> data.isFollower }
        _followingProfilesList.value = isFollowerList.toList()
    }

    private val dummyOtherFollowingQuestionsList = mutableListOf(
        OtherQuestionsData(
            userId = "1",
            category = "가치관",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "5",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "2",
            category = "사랑",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "26",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "3",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "4",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "5",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "6",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "7",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "8",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "9",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isbookmarked = false
        ),
        OtherQuestionsData(
            userId = "10",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isbookmarked = false
        )
    )

    fun setDummyOtherFollowingQuestions() {
        _otherFollowingQuestionsList.value = dummyOtherFollowingQuestionsList.toMutableList()
    }

    fun plusDummyOtherFollowingQuestions() {
        val plusOtherFollowingQuestionsList = listOf(
            OtherQuestionsData(
                userId = "11",
                category = "가치관",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "5",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "12",
                category = "사랑",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "26",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "13",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "14",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "15",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "16",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "17",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "18",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "19",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isbookmarked = false
            ),
            OtherQuestionsData(
                userId = "20",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isbookmarked = false
            )
        )
        dummyOtherFollowingQuestionsList.addAll(plusOtherFollowingQuestionsList.toMutableList())
        _otherFollowingQuestionsList.value = dummyOtherFollowingQuestionsList.toMutableList()
    }
}