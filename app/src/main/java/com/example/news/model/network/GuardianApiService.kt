package com.example.news.model.network


interface GuardianApiService {
    suspend fun searchArticles(query: String): ApiResponse
}