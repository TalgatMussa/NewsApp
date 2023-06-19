package com.example.newsapp.ui.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.repository.Repository
import com.example.newsapp.data.repository.RepositoryImpl
import javax.inject.Inject

class NewsViewModelProviderFactory @Inject constructor(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}