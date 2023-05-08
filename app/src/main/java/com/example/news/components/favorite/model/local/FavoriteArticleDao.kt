package com.example.news.components.favorite.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteArticle(favoriteArticle: FavoriteArticle)

    @Query("DELETE FROM favorite_articles WHERE id = :articleId")
    suspend fun removeFavoriteArticle(articleId: String)

    @Query("SELECT * FROM favorite_articles")
    fun getFavoriteArticles(): Flow<List<FavoriteArticle>>
}