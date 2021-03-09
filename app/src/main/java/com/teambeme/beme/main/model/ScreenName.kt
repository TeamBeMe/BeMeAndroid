package com.teambeme.beme.main.model

enum class ScreenName(
    private val index: Int,
    private val screenName: String
) {
    HOME(0, "HomeFragment"),
    EXPLORE(1, "ExploreFragment"),
    FOLLOWING(2, "FollowingFragment"),
    MYPAGE(3, "MyPageFragment");

    companion object {
        fun of(index: Int): String {
            return values().find { index == it.index }?.screenName
                ?: throw IllegalArgumentException("도대체 이 숫자는 어디서 나온거죠? 고양이 수: $index")
        }
    }
}