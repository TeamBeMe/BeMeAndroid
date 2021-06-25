package com.teambeme.beme.data.repository

import com.teambeme.beme.data.remote.datasource.NoticeDataSource
import com.teambeme.beme.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource
) : NoticeRepository {
    override fun notice(page: Int?) =
        noticeDataSource.notice(page)
}
