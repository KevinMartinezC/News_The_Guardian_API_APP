package com.example.news.components.search.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.network.model.Article
import com.example.news.data.network.model.Filter
import com.example.news.data.network.GuardianApiService
import com.example.news.data.network.utils.GuardianPagingSource
import kotlinx.coroutines.flow.Flow

class GuardianRepositoryImpl(private val apiService: GuardianApiService) : GuardianRepository {

    private fun createPager(query: String, filter: Filter): Pager<Int, Article> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,//MOVE IT IN A CONSTANT
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GuardianPagingSource(apiService, query, filter) }
        )
    }

    override suspend fun searchArticles(query: String, filter: Filter): Flow<PagingData<Article>> {
        return createPager(query, filter).flow
    }
}


