package com.teambeme.beme.otherpage.repository

import com.teambeme.beme.data.remote.datasource.OtherPageDataSource
import com.teambeme.beme.otherpage.model.ResponseFollow
import com.teambeme.beme.otherpage.model.ResponseOtherInfo
import com.teambeme.beme.otherpage.model.ResponseScrap
import retrofit2.Call

class OtherPageRepositoryImpl(private val otherDataSource: OtherPageDataSource) :
    OtherPageRepository {
    override fun getProfileAnswer(token: String, userId: Int, page: Int) =
        otherDataSource.getProfileAnswer(token, userId, page)

    override fun getOtherInfo(token: String, userId: Int): Call<ResponseOtherInfo> =
        otherDataSource.getOtherInfo(token, userId)

    override fun putScrap(token: String, answerId: Int): Call<ResponseScrap> =
        otherDataSource.putScrap(token, answerId)

    override fun putFollow(token: String, userId: Int): Call<ResponseFollow> =
        otherDataSource.putFollow(token, userId)
}
