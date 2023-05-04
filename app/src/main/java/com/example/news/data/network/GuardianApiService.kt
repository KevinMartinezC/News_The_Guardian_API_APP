package com.example.news.data.network


interface GuardianApiService {
    suspend fun searchArticles(
        query: String,
        filter: Filter,
        page: Int,
        pageSize: Int
    ): ApiResponse
}
