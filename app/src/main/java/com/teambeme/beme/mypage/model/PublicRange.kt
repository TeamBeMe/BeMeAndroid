package com.teambeme.beme.mypage.model

import com.teambeme.beme.R

enum class PublicRange(
    private val checkId: Int?,
    private val itemId: String
) {
    NONE(-1, "CLICK_ANY_PUBLIC_MPFILTER"),
    ALL(R.id.chip_range_1, "CLICK_ALL_MPFILTER"),
    OPEN(R.id.chip_range_2, "CLICK_OPEN_MPFILTER"),
    PRIVATE(R.id.chip_range_3, "CLICK_PRIVATE_MPFILTER");

    companion object {
        fun asItemId(checkId: Int): String {
            return values().find { it.checkId == checkId }?.itemId
                ?: throw IllegalArgumentException("무슨 범위여 이게 $checkId")
        }
    }
}