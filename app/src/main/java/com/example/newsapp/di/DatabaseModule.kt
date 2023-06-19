package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.db.ArticleDao
import com.example.newsapp.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context
    ): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, "article_db.db").build()
    }
}
