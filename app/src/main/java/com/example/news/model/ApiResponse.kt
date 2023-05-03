package com.example.news.model

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