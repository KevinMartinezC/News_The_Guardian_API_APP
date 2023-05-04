package com.example.news.model.repository

import com.example.news.model.network.ApiResponse
import com.example.news.model.network.GuardianApiService
import com.example.news.model.repository.GuardianRepository

class GuardianRepositoryImpl(private val apiService: GuardianApiService) : GuardianRepository {
    override suspend fun searchArticles(query: String): ApiResponse {
        return apiService.searchArticles(query)
    }
}
