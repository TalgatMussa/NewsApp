package com.example.newsapp.data.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.data.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}
