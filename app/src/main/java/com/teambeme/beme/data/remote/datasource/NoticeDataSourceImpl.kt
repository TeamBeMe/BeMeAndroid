package com.teambeme.beme.data.remote.datasource

import android.util.Log
import com.teambeme.beme.data.remote.api.NoticeService
import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call
import javax.inject.Inject

class NoticeDataSourceImpl @Inject constructor(
    private val service: NoticeService
) : NoticeDataSource {
    override fun notice(page: Int?): Call<ResponseNoticeData> {
        val call = service.notice(page)
        Log.d("Network Repo", "$call")
        return call
    }
}