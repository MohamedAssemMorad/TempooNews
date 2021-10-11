package com.example.newproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.data.model.NewsResponse
import com.example.newproject.data.repository.NewsRepository

public class NewsListViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    fun callGetNewsList(
        searchQuery: String
    ): LiveData<NewsResponse?> {
        var str = searchQuery
        if (str.isNullOrEmpty())
            str = "bitcoin"
        return repository.callGetNewsList(str)
    }
}