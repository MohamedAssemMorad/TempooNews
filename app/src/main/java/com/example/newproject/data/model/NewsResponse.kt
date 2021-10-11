package com.example.newproject.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsResponse(

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: ArrayList<ArticleResponse>? = null

)