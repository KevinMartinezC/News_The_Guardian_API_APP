package com.example.news.model.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class GuardianPagingSource(
    private val guardianApiService: GuardianApiService,
    private val query: String,
    private val filter: Filter
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            delay(2000)
            val response = guardianApiService.searchArticles(query, filter, page, params.loadSize)
            LoadResult.Page(
                data = response.response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

