package com.teambeme.beme.base

import androidx.paging.PagingSource
import androidx.paging.PagingState

/*
* Created By HyunWoo Lee
* on 6/12, 2021
*
* BeMePagingSource: Base PagingSource Class
*
* How to use
*
* You should inherit this class when you use PagingSource of Paging3
* Just implement override load function
*
* */
abstract class BeMePagingSource<V : Any> : PagingSource<Int, V>() {
    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

const val NETWORK_PAGE_SIZE = 10
