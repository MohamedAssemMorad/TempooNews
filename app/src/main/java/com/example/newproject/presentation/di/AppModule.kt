package com.example.newproject.presentation.di

import com.example.newproject.data.network.NewsAPIController
import com.example.newproject.data.repository.NewsRepository
import com.example.newproject.presentation.viewmodel.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { NewsAPIController }
}

val repositoryModule = module {
    single { NewsRepository(get()) }
}

val viewModelModule = module {
    viewModel { NewsListViewModel(get()) }
}