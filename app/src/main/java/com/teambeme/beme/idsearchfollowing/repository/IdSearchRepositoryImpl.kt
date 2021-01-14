package com.teambeme.beme.idsearchfollowing.repository

import com.teambeme.beme.data.remote.datasource.IdSearchDataSource

class IdSearchRepositoryImpl(private val idSearchDataSource: IdSearchDataSource) :
    IdSearchRepository {
    override fun idSearch(token: String, query: String, range: String?) = idSearchDataSource.idSearch(token, query, range)

    override fun getRecentSearchRecord(token: String) = idSearchDataSource.getRecentSearchRecord(token)

    override fun deleteRecentSearchRecord(token: String, searchedId: Int) = idSearchDataSource.deleteRecentSearchRecord(token, searchedId)
}