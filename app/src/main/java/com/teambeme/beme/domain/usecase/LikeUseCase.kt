package com.teambeme.beme.domain.usecase

import com.teambeme.beme.data.remote.datasource.ExploreDataSource
import javax.inject.Inject

interface LikeUseCase {
    suspend fun changeLikeStatus(answerId: Int): Boolean
}

class LikeUseCaseImpl @Inject constructor(
    private val dataSource: ExploreDataSource
) : LikeUseCase {
    override suspend fun changeLikeStatus(answerId: Int): Boolean {
        if (dataSource.changeLikeStatus(answerId) == null) {
            return false
        }
        return requireNotNull(dataSource.changeLikeStatus(answerId))
    }
}
