package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.NoticeDataSource

class NoticeRepositoryImpl(private val noticeDataSource: NoticeDataSource) :
    NoticeRepository {
    override fun notice(page: Int?) =
        noticeDataSource.notice(page)
}