package com.teambeme.beme.base

import androidx.paging.PagingSource
import androidx.paging.PagingState

/*
* Created By HyunWoo Lee
* on 6/12, 2021
*
* BeMePagingSource: Base PagingSource Class
*
* */
class BeMePagingSource<V : Any>(
    private val pagingDataFactory: suspend (Int) -> List<V>
) : PagingSource<Int, V>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val position = params.key ?: STARTING_PAGE_INDEX

        val response = runCatching {
            pagingDataFactory.invoke(position)
        }.getOrElse { return LoadResult.Error(it) }
        val nextKey = if (response.isEmpty()) null
        else position + (params.loadSize / NETWORK_PAGE_SIZE)

        return runCatching {
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        }.getOrElse { return LoadResult.Error(it) }
    }

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 10
