package com.example.news.model

interface GuardianRepository {
    suspend fun searchArticles(query: String): ApiResponse
}
