package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.NewsResponse
import retrofit2.Response

interface Repository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>
    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>
    suspend fun upsert(article: Article): Long
    suspend fun deleteArticle(article: Article)
    fun getSavedNews(): LiveData<List<Article>>
}