package com.teambeme.beme.otherpage.repository

import com.teambeme.beme.data.remote.datasource.OtherPageDataSource

class OtherPageRepositoryImpl(private val otherDataSource: OtherPageDataSource) :
    OtherPageRepository {
    override fun getProfileAnswer(token: String, userId: Int, page: Int) =
        otherDataSource.getProfileAnswer(token, userId, page)
}
