package com.teambeme.beme.mypage.model

import com.teambeme.beme.R

enum class CategoryFilter(
    private val checkId: Int,
    private val itemId: String
) {
    NONE(-1, "CLICK_ANY_CATEGORY_MPFILTER"),
    VALUES(R.id.chip_write_1, "CLICK_VALUES_MPFILTER"),
    RELATIONSHIP(R.id.chip_write_2, "CLICK_RELATIONSHIP_MPFILTER"),
    LOVE(R.id.chip_write_3, "CLICK_LOVE_MPFILTER"),
    DAILYLIFE(R.id.chip_write_4, "CLICK_DAILYLIFE_MPFILTER"),
    ME(R.id.chip_write_5, "CLICK_ME_MPFILTER"),
    STORY(R.id.chip_write_6, "CLICK_STORY_MPFILTER");

    companion object {
        fun asItemId(checkId: Int): String {
            return values().find { it.checkId == checkId }?.itemId
                ?: throw IllegalArgumentException("무슨 범위여 이게 $checkId")
        }
    }
}