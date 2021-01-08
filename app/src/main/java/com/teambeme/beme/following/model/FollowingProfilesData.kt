package com.teambeme.beme.following.model

data class FollowingProfilesData(
    val profile_img: Int,
    val profile_id: String,
    val isFollowing: Boolean,
    val isFollower: Boolean
)