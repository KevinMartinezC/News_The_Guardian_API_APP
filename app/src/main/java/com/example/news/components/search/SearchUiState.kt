package com.example.news.components.search

import com.example.news.data.network.Article

data class SearchUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false
)
