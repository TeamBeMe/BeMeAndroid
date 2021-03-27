package com.teambeme.beme.data.repository

import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call

interface NoticeRepository {
    fun notice(page: Int?): Call<ResponseNoticeData>
}
