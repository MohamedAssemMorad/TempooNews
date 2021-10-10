package com.example.newproject.data.network

import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    fun callGetMovieList(
        @Path("id") id: Int?,
        @Query("page") pageNum: Int,
        @Query("sort_by") sortBy: String
    )
}