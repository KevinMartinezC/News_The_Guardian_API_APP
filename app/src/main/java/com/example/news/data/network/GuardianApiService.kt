package com.example.news.data.network

import com.example.news.data.network.model.Filter


interface GuardianApiService {
    suspend fun searchArticles(
        query: String,
        filter: Filter,
        page: Int,
        pageSize: Int
    ): ApiResponse
}
