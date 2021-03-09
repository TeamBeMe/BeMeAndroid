package com.teambeme.beme.util

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

fun recordClickEvent(contentType: String, itemId: String) {
    Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
        param(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        param(FirebaseAnalytics.Param.ITEM_ID, itemId)
    }
}