package com.example.news.model.repository

import com.example.news.model.network.ApiResponse

interface GuardianRepository {
    suspend fun searchArticles(query: String): ApiResponse
}
