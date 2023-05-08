package com.example.news.components.search.model.repository

import androidx.paging.PagingData
import com.example.news.data.network.model.Article
import com.example.news.data.network.model.Filter
import kotlinx.coroutines.flow.Flow

interface GuardianRepository {
    suspend fun searchArticles(query: String, filter: Filter): Flow<PagingData<Article>>
}


