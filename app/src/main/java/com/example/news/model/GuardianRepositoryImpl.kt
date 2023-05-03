package com.example.news.model

class GuardianRepositoryImpl(private val apiService: GuardianApiService) : GuardianRepository {
    override suspend fun searchArticles(query: String): ApiResponse {
        return apiService.searchArticles(query)
    }
}
