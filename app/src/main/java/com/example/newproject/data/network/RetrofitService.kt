package com.example.newproject.data.network

import com.example.newproject.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("v2/everything")
    fun callGetAllNews(
        @Query("q") searchQuery: String
    ): Call<NewsResponse>
}