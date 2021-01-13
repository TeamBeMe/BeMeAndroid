package com.teambeme.beme.notification.repository

import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call

interface NoticeRepository {
    fun notice(token: String, page: Int?): Call<ResponseNoticeData>
}
