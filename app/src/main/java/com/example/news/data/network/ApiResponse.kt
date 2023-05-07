package com.example.news.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseContent(
    val status: String,
    val total: Int,
    val results: List<Article>
)

@Serializable
data class ApiResponse(
    val response: ApiResponseContent
)