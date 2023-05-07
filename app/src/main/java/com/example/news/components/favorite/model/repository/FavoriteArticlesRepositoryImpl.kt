package com.example.news.components.favorite.model.repository

import com.example.news.components.favorite.model.local.FavoriteArticle
import com.example.news.components.favorite.model.local.FavoriteArticleDao
import kotlinx.coroutines.flow.Flow

class FavoriteArticlesRepositoryImpl(
    private val favoriteArticleDao: FavoriteArticleDao
) : FavoriteArticlesRepository {

    override val favoriteArticles: Flow<List<FavoriteArticle>> = favoriteArticleDao.getFavoriteArticles()

    override suspend fun addFavoriteArticle(favoriteArticle: FavoriteArticle) {
        favoriteArticleDao.addFavoriteArticle(favoriteArticle)
    }

    override suspend fun removeFavoriteArticle(articleId: String) {
        favoriteArticleDao.removeFavoriteArticle(articleId)
    }
}