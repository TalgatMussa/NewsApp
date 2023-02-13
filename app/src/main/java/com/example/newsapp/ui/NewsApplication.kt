package com.example.newsapp.ui

import android.app.Application
import com.example.newsapp.db.ArticleDatabase

class NewsApplication: Application() {
    val database: ArticleDatabase by lazy {
        ArticleDatabase.getDatabase(this)
    }
}