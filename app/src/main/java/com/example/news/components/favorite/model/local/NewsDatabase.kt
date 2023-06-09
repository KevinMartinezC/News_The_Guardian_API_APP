package com.example.news.components.favorite.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteArticle::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}
