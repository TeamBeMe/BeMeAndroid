package com.teambeme.beme.data.remote.datasource

import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call

interface NoticeDataSource {
    fun notice(page: Int?): Call<ResponseNoticeData>
}
