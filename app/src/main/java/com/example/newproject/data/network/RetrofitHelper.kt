package com.example.newproject.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val APP_BASE_URL = "https://newsapi.org/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    private const val ACCESS_TOKEN = "a442795bc3c74ef18b32be2f063f3052"
    private var appInstance: RetrofitService? = null

    fun getInstance(): RetrofitService? {
        if (appInstance == null) {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(OAuthInterceptor("Bearer ", ACCESS_TOKEN))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            appInstance = retrofit.create()
        }
        return appInstance
    }
}