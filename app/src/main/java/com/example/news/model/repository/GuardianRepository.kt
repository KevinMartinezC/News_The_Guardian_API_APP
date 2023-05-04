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

interface GuardianRepository {
    suspend fun searchArticles(query: String, filter: Filter): Flow<PagingData<Article>>
}


