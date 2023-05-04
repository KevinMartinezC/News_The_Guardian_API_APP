package com.example.news.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.model.network.ApiResponse
import com.example.news.model.network.Article
import com.example.news.model.network.Filter
import com.example.news.model.network.GuardianApiService
import com.example.news.model.network.GuardianPagingSource
import kotlinx.coroutines.flow.Flow

class GuardianRepositoryImpl(private val apiService: GuardianApiService) : GuardianRepository {
    override suspend fun searchArticles(query: String, filter: Filter): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GuardianPagingSource(apiService, query, filter) }
        ).flow
    }
}

