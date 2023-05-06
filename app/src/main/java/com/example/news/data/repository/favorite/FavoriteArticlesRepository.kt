package com.example.news.data.repository.favorite

import com.example.news.data.local.FavoriteArticle
import com.example.news.data.local.FavoriteArticleDao
import kotlinx.coroutines.flow.Flow

class FavoriteArticlesRepository(private val favoriteArticleDao: FavoriteArticleDao) {
    val favoriteArticles: Flow<List<FavoriteArticle>> = favoriteArticleDao.getFavoriteArticles()

    suspend fun addFavoriteArticle(favoriteArticle: FavoriteArticle) {
        favoriteArticleDao.addFavoriteArticle(favoriteArticle)
    }

    suspend fun removeFavoriteArticle(articleId: String) {
        favoriteArticleDao.removeFavoriteArticle(articleId)
    }
}
