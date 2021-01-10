package com.teambeme.beme.otherpage.repository

import com.teambeme.beme.data.remote.datasource.OtherPageDataSource

class OtherPageRepositoryImpl(private val otherDataSource: OtherPageDataSource) :
    OtherPageRepository {
    override fun getProfileAnswer(token: String, user_id: Int, page: Int) =
        otherDataSource.getProfileAnswer(token, user_id, page)
}
