package com.example.news.components.favorite.model.repository

import com.example.news.components.favorite.model.local.FavoriteArticle
import kotlinx.coroutines.flow.Flow

interface FavoriteArticlesRepository {

    val favoriteArticles: Flow<List<FavoriteArticle>>

    suspend fun addFavoriteArticle(favoriteArticle: FavoriteArticle)

    suspend fun removeFavoriteArticle(articleId: String)
}