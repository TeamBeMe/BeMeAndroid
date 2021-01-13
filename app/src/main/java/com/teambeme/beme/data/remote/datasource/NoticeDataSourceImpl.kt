package com.teambeme.beme.data.remote.datasource

import android.util.Log
import com.teambeme.beme.data.remote.api.NoticeService
import com.teambeme.beme.notification.model.ResponseNoticeData
import retrofit2.Call

class NoticeDataSourceImpl(private val service: NoticeService) :
    NoticeDataSource {
    override fun notice(token: String, page: Int?): Call<ResponseNoticeData> {
        Log.d("Network Repo", "$token and $page")
        val call = service.notice(token, page)
        Log.d("Network Repo", "$call")
        return call
    }
}