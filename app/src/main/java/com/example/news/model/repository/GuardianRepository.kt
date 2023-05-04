package com.example.news.model.repository

import com.example.news.model.network.ApiResponse
import com.example.news.model.network.Filter

interface GuardianRepository {
    suspend fun searchArticles(query: String, section: Filter): ApiResponse
}
