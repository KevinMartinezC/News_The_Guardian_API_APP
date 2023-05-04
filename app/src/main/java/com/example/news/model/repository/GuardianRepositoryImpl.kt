package com.example.news.model.repository

import com.example.news.model.network.ApiResponse
import com.example.news.model.network.Filter
import com.example.news.model.network.GuardianApiService

class GuardianRepositoryImpl(private val apiService: GuardianApiService) : GuardianRepository {
    override suspend fun searchArticles(
        query: String,
        section: Filter,
    ): ApiResponse {
        return apiService.searchArticles(query, section)
    }
}
