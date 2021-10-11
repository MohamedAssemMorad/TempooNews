package com.example.newproject.data.repository

import androidx.lifecycle.LiveData
import com.example.newproject.data.model.NewsResponse
import com.example.newproject.data.network.NewsAPIController

open class NewsRepository(private val apiController: NewsAPIController) {

    fun callGetNewsList(
        searchQuery: String
    ): LiveData<NewsResponse?> {
        return (apiController).getNewsList(searchQuery)
    }

}