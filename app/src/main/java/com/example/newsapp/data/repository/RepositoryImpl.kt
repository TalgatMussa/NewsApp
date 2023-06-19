package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.db.ArticleDatabase
import com.example.newsapp.data.models.Article
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val articleDatabase: ArticleDatabase
): Repository {
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode = countryCode, pageNumber = pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchNews(searchQuery = searchQuery, pageNumber = pageNumber)

    override suspend fun upsert(article: Article) =
        articleDatabase.getArticleDao().upsert(article)

    override fun getSavedNews() =
        articleDatabase.getArticleDao().getAllArticles()

    override suspend fun deleteArticle(article: Article) =
        articleDatabase.getArticleDao().deleteArticle(article)


}