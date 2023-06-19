package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.ui.fragments.article.ArticleFragment
import com.example.newsapp.ui.fragments.breaking_news.BreakingNewsFragment
import com.example.newsapp.ui.fragments.saved_news.SavedNewsFragment
import com.example.newsapp.ui.fragments.search_news.SearchNewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppBindModule::class, DatabaseModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }

    fun inject(breakingNewsFragment: BreakingNewsFragment)
    fun inject(savedNewsFragment: SavedNewsFragment)
    fun inject(searchNewsFragment: SearchNewsFragment)
    fun inject(articleFragment: ArticleFragment)
}