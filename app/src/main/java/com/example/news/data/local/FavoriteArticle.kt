package com.example.news.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_articles")
data class FavoriteArticle(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "webTitle")
    val webTitle: String,

    @ColumnInfo(name = "webUrl")
    val webUrl: String,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String?
)
