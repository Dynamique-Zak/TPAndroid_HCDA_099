package com.example.tpandroid.article

import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.api.RetrofitTools.Companion.retrofit
import retrofit2.http.GET

interface ArticleService {

    @GET("articles")
    suspend fun getArticles() : ApiResponse<List<Article>>

    object ArticleApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}