package com.example.news.data.repository

import androidx.paging.PagingData
import com.example.news.data.network.Article
import com.example.news.data.network.Filter
import kotlinx.coroutines.flow.Flow

interface GuardianRepository {
    suspend fun searchArticles(query: String, filter: Filter): Flow<PagingData<Article>>
}


