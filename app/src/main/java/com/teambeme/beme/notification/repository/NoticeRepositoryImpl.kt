package com.teambeme.beme.notification.repository

import com.teambeme.beme.data.remote.datasource.NoticeDataSource

class NoticeRepositoryImpl(private val noticeDataSource: NoticeDataSource) :
    NoticeRepository {
    override fun notice(token: String, page: Int?) =
        noticeDataSource.notice(token, page)
}