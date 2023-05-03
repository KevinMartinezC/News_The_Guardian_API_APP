package com.example.news.model

interface GuardianApiService {
    suspend fun searchArticles(query: String): ApiResponse

}