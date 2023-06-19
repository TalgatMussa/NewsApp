package com.example.newsapp.di

import com.example.newsapp.data.repository.Repository
import com.example.newsapp.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}